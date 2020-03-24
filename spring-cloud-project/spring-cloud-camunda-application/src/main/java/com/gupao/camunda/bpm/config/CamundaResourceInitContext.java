package com.gupao.camunda.bpm.config;

import com.gupao.camunda.bpm.delegate.CalculateInterestService;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 设置数据库连接
 */
//@Configuration
public class CamundaResourceInitContext {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String passWord;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        return dataSource;
    }



//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:process-engine;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");
//        return dataSource;
//    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SpringProcessEngineConfiguration engineConfiguration(DataSource dataSource,
                                                                PlatformTransactionManager transactionManager,
                                                                @Value("classpath*:*.bpmn") Resource[] deploymentResources) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();

        configuration.setProcessEngineName("engine");
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setJobExecutorActivate(false);
//        configuration.setDeploymentResources(deploymentResources);

        return configuration;
    }

    @Bean
    public ProcessEngineFactoryBean engineFactory(SpringProcessEngineConfiguration engineConfiguration) {
        ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
        factoryBean.setProcessEngineConfiguration(engineConfiguration);
        return factoryBean;
    }

    @Bean
    public ProcessEngine processEngine(ProcessEngineFactoryBean factoryBean) throws Exception {
        return factoryBean.getObject();
    }

    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    @Bean
    public IdentityService identityService(ProcessEngine processEngine){
        return processEngine.getIdentityService();
    }

    @Bean
    public CalculateInterestService calculateInterestService() {
        return new CalculateInterestService();
    }


//    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//    RepositoryService repositoryService = processEngine.getRepositoryService();
//    RuntimeService runtimeService = processEngine.getRuntimeService();
//    TaskService taskService = processEngine.getTaskService();
//    IdentityService identityService = processEngine.getIdentityService();
//    FormService formService = processEngine.getFormService();
//    HistoryService historyService = processEngine.getHistoryService();
//    ManagementService managementService = processEngine.getManagementService();
//    FilterService filterService = processEngine.getFilterService();
//    ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
//    CaseService caseService = processEngine.getCaseService();
//    DecisionService decisionService = processEngine.getDecisionService();


//1、act_ge_ 通用数据表，ge是general的缩写
//2、act_hi_ 历史数据表，hi是history的缩写，对应HistoryService接口
//3、act_id_ 身份数据表，id是identity的缩写，对应IdentityService接口
//4、act_re_ 流程存储表，re是repository的缩写，对应RepositoryService接口，存储流程部署和流程定义等静态数据
//5、act_ru_ 运行时数据表，ru是runtime的缩写，对应RuntimeService接口和TaskService接口，存储流程实例和用户任务等动态数据
//
//
//表结构操作：
//3.3.1：资源库流程规则表
//1) act_re_deployment 部署信息表
//2) act_re_model 流程设计模型部署表
//3) act_re_procdef 流程定义数据表
//3.3.2：运行时数据库表
//1) act_ru_execution 运行时流程执行实例表
//2) act_ru_identitylink 运行时流程人员表，主要存储任务节点与参与者的相关信息
//3) act_ru_task 运行时任务节点表
//4) act_ru_variable 运行时流程变量数据表
//3.3.3：历史数据库表
//1) act_hi_actinst 历史节点表
//2) act_hi_attachment 历史附件表
//3) act_ih_comment 历史意见表
//4) act_hi_identitylink 历史流程人员表
//5) act_hi_detail 历史详情表，提供历史变量的查询
//6) act_hi_procinst 历史流程实例表
//7) act_hi_taskinst 历史任务实例表
//8) act_hi_varinst 历史变量表
//3.3.4：组织机构表
//1) act_id_group 用户组信息表
//2) act_id_info 用户扩展信息表
//3) act_id_membership 用户与用户组对应信息表
//4) act_id_user 用户信息表
//    这四张表很常见，基本的组织机构管理，关于用户认证方面建议还是自己开发一套，组件自带的功能太简单，使用中有很多需求难以满足
//3.3.5：通用数据表
//1) act_ge_bytearray 二进制数据表
//2) act_ge_property 属性数据表存储整个流程引擎级别的数据,初始化表结构时，会默认插入三条记录，
//            3.4：activiti.cfg.xml（activiti的配置文件）
//    Activiti核心配置文件，配置流程引擎创建工具的基本参数和数据库连接池参数。
//    定义数据库配置参数：
//            jdbcUrl: 数据库的JDBC URL。
//            jdbcDriver: 对应不同数据库类型的驱动。
//            jdbcUsername: 连接数据库的用户名。
//            jdbcPassword: 连接数据库的密码。
//    基于JDBC参数配置的数据库连接 会使用默认的MyBatis连接池。 下面的参数可以用来配置连接池（来自MyBatis参数）：
//            jdbcMaxActiveConnections: 连接池中处于被使用状态的连接的最大值。默认为10。
//            jdbcMaxIdleConnections: 连接池中处于空闲状态的连接的最大值。
//            jdbcMaxCheckoutTime: 连接被取出使用的最长时间，超过时间会被强制回收。 默认为20000（20秒）。
//            jdbcMaxWaitTime: 这是一个底层配置，让连接池可以在长时间无法获得连接时， 打印一条日志，并重新尝试获取一个连接。（避免因为错误配置导致沉默的操作失败）。 默认为20000（20秒）。
//

}
