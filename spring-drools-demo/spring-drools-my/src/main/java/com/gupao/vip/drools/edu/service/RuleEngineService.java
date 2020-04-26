package com.gupao.vip.drools.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleEngineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleEngineService.class) ;

//    public void executeAddRule(QueryParam param) {
//        LOGGER.info("参数数据:"+param.getParamId()+";"+param.getParamSign());
//        ParamInfo paramInfo = new ParamInfo() ;
//        paramInfo.setId(param.getParamId());
//        paramInfo.setParamSign(param.getParamSign());
//        paramInfo.setCreateTime(new Date());
//        paramInfo.setUpdateTime(new Date());
//        ParamInfoService paramInfoService = (ParamInfoService)SpringContextUtil.getBean("paramInfoService") ;
//        paramInfoService.insertParam(paramInfo);
//    }
//
//    public void executeRemoveRule(QueryParam param) {
//        LOGGER.info("参数数据:"+param.getParamId()+";"+param.getParamSign());
//        ParamInfoService paramInfoService = (ParamInfoService)SpringContextUtil.getBean("paramInfoService") ;
//        ParamInfo paramInfo = paramInfoService.selectById(param.getParamId());
//        if (paramInfo != null){
//            paramInfoService.removeById(param.getParamId()) ;
//        }
//    }
}
