package com.gupao.gateway.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name="gateway",urlPatterns = "/gateway")
public class GatewayServlet  extends HttpServlet {

    @Autowired
    private DiscoveryClient discoveryClient;

}
