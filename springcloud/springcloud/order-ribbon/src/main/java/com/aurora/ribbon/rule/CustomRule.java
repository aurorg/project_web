package com.aurora.ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomRule extends AbstractLoadBalancerRule {

    @Override
    public Server choose(Object key) {
        //获得当前请求的服务的实例
        List<Server> reachableServers = getLoadBalancer().getReachableServers();

        int random = ThreadLocalRandom.current().nextInt(reachableServers.size());

        Server server = reachableServers.get(random);

        return server;
    }



    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }


}
