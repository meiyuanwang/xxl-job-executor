package com.magnumresearch.aqumon.core.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class XxlJobConfig {

    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);


	@Value("${xxl.job.admin.addresses:}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken:}")
    private String accessToken;

    @Value("${xxl.job.executor.appname:}")
    private String appname;

    @Value("${xxl.job.executor.address:}")
    private String address;

    @Value("${xxl.job.executor.ip:}")
    private String ip;

    @Value("${xxl.job.executor.port:9999}")
    private int port;

    @Value("${xxl.job.executor.logpath:}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays:30}")
    private int logRetentionDays;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");

        initXxlJobConfig();

        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setAddress(address);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;
    }

    private void initXxlJobConfig(){
        InputStream is=   XxlJobConfig.class.getResourceAsStream("/executor.properties");
        Properties p=new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(adminAddresses == null || adminAddresses.length() == 0){
            adminAddresses = p.getProperty("xxl.job.admin.addresses");
        }
        if(accessToken == null || accessToken.length() == 0){
            accessToken = p.getProperty("xxl.job.accessToken");
        }
        if(appname == null || appname.length() == 0){
            appname = p.getProperty("xxl.job.executor.appname");
        }
        if(address == null || address.length() == 0){
            address = p.getProperty("xxl.job.executor.address");
        }
        if(ip == null || ip.length() == 0){
            ip = p.getProperty("xxl.job.executor.ip");
        }
        if(logPath == null || logPath.length() == 0){
            logPath = p.getProperty("xxl.job.executor.logpath");
        }
    }
}

