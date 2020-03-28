package com.gupao.microservicesproject;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.gupao.microservicesproject.graphql.entity.Card;
import com.gupao.microservicesproject.graphql.entity.User;
import com.gupao.microservicesproject.httputil.HttpClientUtils;
import com.gupao.microservicesproject.httputil.OkHttpUtils;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.commons.io.IOUtils;
import org.mountcloud.graphql.GraphqlClient;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.GraphqlQuery;
import org.mountcloud.graphql.response.GraphqlResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MySpringApplication {

	public static void main(String[] args) {

//		SpringApplication.run(MySpringApplication.class, args);
//		new SpringApplicationBuilder(MySpringApplication.class)
//				.properties("server.port:8090")
//				.run(args);
		SpringApplication springApplication =new SpringApplication(MySpringApplication.class);
//		//设置为非web应用
		springApplication.setWebApplicationType(WebApplicationType.NONE);
//		ConfigurableApplicationContext context = springApplication.run(args);
		//当前上下文加载类：org.springframework.context.annotation.AnnotationConfigApplicationContext
//		System.out.println("当前上下文加载类："+context.getClass().getName());
//		try {
//			graphQLSDLDemo2();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		graphQLQuery();

//		HttpClientUtils.tesPostGraphql();

//
//		POST /graphql HTTP/1.1
//		Host: localhost:8082
//		Content-Type: application/json
//
//		{"query":"query \n{\n    countBooks\n}","variables":{}}

//		OkHttpUtils.testGraphql();

//		testDataTransfer();

//		dataTransfer();
	}


	public static void dataTransfer(){
		BufferedWriter bw = null;
		BufferedReader reader = null;
		try {
//			写文件
			File csv = new File("D:\\test\\writers.csv"); // CSV数据文件
			bw = new BufferedWriter(new FileWriter(csv, true));
			//读文件
			reader = new BufferedReader(new FileReader("D:\\test\\read.csv"));//换成你的文件名
			reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
			String line = null;
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

				String num = item[item.length-1];//这就是你要的数据了
				String myText = num+ "," +"张三" + "," + "2000" + "," + "2004";
//				int value = Integer.parseInt(last);//如果是数值，可以转化为数值
//				System.out.println(last);

				// 添加新的数据行
				bw.write(myText);
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void test1(){
		//query
//		public static GraphQLObjectType userQuery(){
//		//getUser 返回构造的user对象
//		//new GraphQLList 代表返回的是List<User> 为了测试，这里只放一个
//			return newObject()
//					.name("userQuery")
//					.field(newFieldDefinition().type(new GraphQLList(getUserType())).name("user").staticValue(getUsers()))
//					.build();
//		}
//		//schema
//		public static void mainExec() throws InterruptedException {
//		//创建Schema
//			GraphQLSchema schema = GraphQLSchema.newSchema()
//					.query(userQuery())
//					.build();
////测试输出
//			GraphQL graphQL = GraphQL.newGraphQL(schema).build();
//			Map<String, Object> result = graphQL.execute("query userQuery{user{id,age,dogs}}").getData();
//
//		GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
//		ExecutionResult executionResult = build.execute("{hello}");
	}

	public static void testDataTransfer(){
		String msg="{\n" +
				"\t\"data\":{\t\n" +
				"\t\t\"ssi\":[\t\n" +
				"\t\t\t{\n" +
				"\t\t\t\t\"finame\":\"1231\",\n" +
				"\t\t\t\t\"num\":12\n" +
				"\t\t\t},\n" +
				"\t\t\t{\n" +
				"\t\t\t\t\"finame\":\"jim\",\n" +
				"\t\t\t\t\"num\":18\n" +
				"\t\t\t}\n" +
				"\t\t]\n" +
				"\t}\n" +
				"}";
		Gson gosn =new Gson();
		Map<String,Object> map= new HashMap<>();
		map = gosn.fromJson(msg,map.getClass());
	}


	public enum Gender{
		MAN
	}

	public static void graphQLQuery(){
		String serverUrl = "http://localhost:8082/graphql";
		GraphqlClient graphqlClient = GraphqlClient.buildGraphqlClient(serverUrl);
		String queryMethodName = "findAuthorsByFilter";  //findAuthorsByFilter  findOneAuthor  findAllAuthors
		GraphqlQuery query = new DefaultGraphqlQuery(queryMethodName);

		List<Map<String,Object>> filterList = new ArrayList<>();
		Map<String,Object> map1 =new HashMap<>();
		map1.put("firstName","Jim");
		map1.put("lastName","Green");
		map1.put("gender",Gender.MAN);
//		String[] value = "CE,YU,NP".split(",");
//		map1.put("value",new String[]{"CE","YU"});
//		map1.put("value",value);
		/**
		 * {"query":"
		 *        {
		 * 		findAuthorsByFilter(filter:[{firstName:\"Jim\",lastName:\"Green\",value:[\"CE\",\"YU\"]}],first:3){
		 * 			firstName
		 * 			lastName}
		 *    }
		 * "}
		 *
		 * {"query":"
		 * {findAuthorsByFilter(filter:[{firstName:\"Jim\",lastName:\"Green\",value:[\"CE\",\"YU\",\"NP\"]}],first:3){}}
		 * "}
		 *
		 *  Query failed to validate : '{findAuthorsByFilter(filter:[{firstName:"Jim",lastName:"Green",value:["CE","YU","NP"]}],first:3){firstName lastName}}'
		 */
		filterList.add(map1);
		query.getRequestParameter().addObjectParameter("first",3).addObjectParameter("filter",filterList);

//		query.getRequestParameter().addObjectParameter("id",8);
		query.addResultAttributes("firstName","lastName");
		try {
			//执行query
			GraphqlResponse response = graphqlClient.doQuery(query);
			//获取数据，数据为map类型
			Map result = response.getData();
			Map m2 = (Map)result.get("data");


			List<Map> findAllAuthors = (List<Map>)m2.get(queryMethodName);
			for (Map map:findAllAuthors){
				System.out.println(map.get("firstName").toString()+"----" +map.get("lastName").toString());
			}

//			System.out.println(m2==null);
//
//			Map last =  (Map)m2.get(queryMethodName);
//			System.out.println(last.get("firstName").toString()+"----" +last.get("lastName").toString());



		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void graphQLSDLDemo2() throws Exception {
		//读取graphqls文件
//		String fileName = "user.graphqls";
		String path = "D:\\ideaspace2019\\microservices-project\\spring-application\\src\\main\\resources\\graphsql\\user.graphqls";
		File file = new File(path);
		String fileContent = IOUtils.toString(new FileInputStream(file) , "UTF-8");
		//解析文件
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(fileContent);

		RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
				.type("UserQuery", builder -> {
							return builder.dataFetcher("user", environment -> {
								Long id = environment.getArgument("id");
								Card card = new Card("123456", id);
								return new User(18, id, "user0" + id, card);
							});
						}
				)
				.build();

		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);

		GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();

		String query = "{user(id:14){id,name,age,card{cardNumber,userId}}}";
		ExecutionResult result = graphQL.execute(query);

		System.out.println("query: " + query);
		System.out.println(result.toSpecification());
	}

}
