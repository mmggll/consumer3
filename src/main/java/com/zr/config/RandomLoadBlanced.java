package com.zr.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by 86151 on 2019/3/7.
 */
public class RandomLoadBlanced extends AbstractLoadBalancerRule  {

    /**
     * Randomly choose from all living servers
     */
    //把随机策略改造为 轮询访问每台服务器5次后在切换为下一台服务器继续访问五次

    //lb是传过来的服务列表信息

    private int total=0;
    private int currencytotal = 0;
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        //如果传过来的服务为空
        Server server = null;

        while (server == null) {
            //如果线程中断
            if (Thread.interrupted()) {
                return null;
            }
            //要返回的服务集合
            List<Server> upList = lb.getReachableServers();
            //获取到的服务详细信息
            List<Server> allList = lb.getAllServers();

            //获取到所有的服务数量
            int serverCount = allList.size();
            //如果服务数量为0
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
            //随机选择不同的下标
//            int index = chooseRandomInt(serverCount);
            //获取随即后的服务信息
//            server = upList.get(index);

            if (total<5){
                server = upList.get(currencytotal);
                total++;
            }else{
                currencytotal++;
                total = 0;
                if (currencytotal>=upList.size()){
                    currencytotal=0;
                }
            }
            //如果服务为空
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                //线程中断一会
                Thread.yield();
                continue;
            }
            //如果存在，直接返回服务
            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }
}

