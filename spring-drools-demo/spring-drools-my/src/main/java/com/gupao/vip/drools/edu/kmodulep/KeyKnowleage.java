package com.gupao.vip.drools.edu.kmodulep;

public class KeyKnowleage {

    /**
     * Example 1. An empty kmodule.xml file-----------------------
     * <?xml version="1.0" encoding="UTF-8"?>
     * <kmodule xmlns="http://www.drools.org/xsd/kmodule"/>
     *
     * Example 2. Creating a KieContainer from the classpath  -------------------
     *
     * KieServices kieServices = KieServices.Factory.get();
     * KieContainer kContainer = kieServices.getKieClasspathContainer();
     *
     * Note:` KieServices` is the interface from where it possible to access all the Kie building and runtime facilities:
     *
     * KieBase kBase1 = kContainer.getKieBase("KBase1");
     * KieSession kieSession1 = kContainer.newKieSession("KSession2_1");
     * StatelessKieSession kieSession2 = kContainer.newStatelessKieSession("KSession2_2");
     *
     *
     * Example 5. Retrieving default KieBases and KieSessions from the KieContainer --------------------------
     *
     * KieBase kBase1 = kContainer.getKieBase(); // returns KBase1
     * KieSession kieSession1 = kContainer.newKieSession(); // returns KSession2_1
     *
     * Example 6. Creating a KieContainer of an existing project by ReleaseId------------------------------
     *
     * KieServices kieServices = KieServices.Factory.get();
     * ReleaseId releaseId = kieServices.newReleaseId( "org.acme", "myartifact", "1.0" );
     * KieContainer kieContainer = kieServices.newKieContainer( releaseId );
     *
     *
     * Example 8. Creating a kmodule.xml programmatically and adding it to a KieFileSystem-----------------------------
     *
     * KieServices kieServices = KieServices.Factory.get();
     * KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
     *
     * KieBaseModel kieBaseModel1 = kieModuleModel.newKieBaseModel( "KBase1 ")
     *         .setDefault( true )
     *         .setEqualsBehavior( EqualityBehaviorOption.EQUALITY )
     *         .setEventProcessingMode( EventProcessingOption.STREAM );
     *
     * KieSessionModel ksessionModel1 = kieBaseModel1.newKieSessionModel( "KSession1" )
     *         .setDefault( true )
     *         .setType( KieSessionModel.KieSessionType.STATEFUL )
     *         .setClockType( ClockTypeOption.get("realtime") );
     *
     * KieFileSystem kfs = kieServices.newKieFileSystem();
     * kfs.writeKModuleXML(kieModuleModel.toXML());
     *
     *
     * Example 9. Adding Kie artifacts to a KieFileSystem------------------------------------------------------------
     *
     * KieFileSystem kfs = ...
     * kfs.write( "src/main/resources/KBase1/ruleSet1.drl", stringContainingAValidDRL )
     *         .write( "src/main/resources/dtable.xls",
     *                 kieServices.getResources().newInputStreamResource( dtableFileStream ) );
     *
     *
     * KieFileSystem kfs = ...
     * kfs.write( "src/main/resources/myDrl.txt",
     *            kieServices.getResources().newInputStreamResource( drlStream )
     *                       .setResourceType(ResourceType.DRL)
     *
     * Example 11. Building the contents of a KieFileSystem and creating a KieContainer -----------------------------------
     *
     * KieServices kieServices = KieServices.Factory.get();
     * KieFileSystem kfs = ...
     * kieServices.newKieBuilder( kfs ).buildAll();
     * KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
     *
     *
     * Example 14. Creating a new KieBase with a custom ClassLoader----------------------------------------------------------
     * KieServices kieServices = KieServices.Factory.get();
     * KieBaseConfiguration kbaseConf = kieServices.newKieBaseConfiguration( null, MyType.class.getClassLoader() );
     * KieBase kbase = kieContainer.newKieBase( kbaseConf );
     *
     * Example 15. Registering and starting a KieScanner on a KieContainer--------------------------------------------------
     * KieServices kieServices = KieServices.Factory.get();
     * ReleaseId releaseId = kieServices.newReleaseId( "org.acme", "myartifact", "1.0-SNAPSHOT" );
     * KieContainer kContainer = kieServices.newKieContainer( releaseId );
     * KieScanner kScanner = kieServices.newKieScanner( kContainer );
     *
     * // Start the KieScanner polling the Maven repository every 10 seconds
     * kScanner.start( 10000L );
     *
     *
     * Example 16. Getting a KieBase from a KieContainer--------------------------------------------------------------
     * KieBase kBase = kContainer.getKieBase();
     * KieSession ksession = kbase.newKieSession();
     *
     *
     * Example 18. Adding an AgendaEventListener-------------------------------------------------------------------
     *
     * ksession.addEventListener( new DefaultAgendaEventListener() {
     *     public void afterMatchFired(AfterMatchFiredEvent event) {
     *         super.afterMatchFired( event );
     *         System.out.println( event );
     *     }
     * });
     *
     *
     * Example 20. FileLogger-----------------------------------------------------------------------------------------
     *
     * KieRuntimeLogger logger =
     *   KieServices.Factory.get().getLoggers().newFileLogger(ksession, "logdir/mylogfile");
     * ...
     * logger.close();
     *
     *
     * Example 21. Set Global Command-----------------------------------------------------------------------------------------
     * StatelessKieSession ksession = kbase.newStatelessKieSession();
     * ExecutionResults bresults =
     *     ksession.execute( CommandFactory.newSetGlobal( "stilton", new Cheese( "stilton" ), true);
     * Cheese stilton = bresults.getValue( "stilton" );
     *
     * Example 22. Get Global Command-----------------------------------------------------------------------------------------
     *
     * StatelessKieSession ksession = kbase.newStatelessKieSession();
     * ExecutionResults bresults =  ksession.execute( CommandFactory.getGlobal( "stilton" );
     * Cheese stilton = bresults.getValue( "stilton" );
     *
     *
     * Example 23. BatchExecution Command --------------------------------------------------------------------------------
     *
     * StatelessKieSession ksession = kbase.newStatelessKieSession();
     *
     * List cmds = new ArrayList();
     * cmds.add( CommandFactory.newInsertObject( new Cheese( "stilton", 1), "stilton") );
     * cmds.add( CommandFactory.newStartProcess( "process cheeses" ) );
     * cmds.add( CommandFactory.newQuery( "cheeses" ) );
     * ExecutionResults bresults = ksession.execute( CommandFactory.newBatchExecution( cmds ) );
     * Cheese stilton = ( Cheese ) bresults.getValue( "stilton" );
     * QueryResults qresults = ( QueryResults ) bresults.getValue( "cheeses" );
     *
     *
     *
     * Example 27. Out identifiers---------------------------------------------------------------------------------------------
     * // Set up a list of commands
     * List cmds = new ArrayList();
     * cmds.add( CommandFactory.newSetGlobal( "list1", new ArrayList(), true ) );
     * cmds.add( CommandFactory.newInsert( new Person( "jon", 102 ), "person" ) );
     * cmds.add( CommandFactory.newQuery( "Get People", "getPeople" ) );
     *
     * // Execute the list
     * ExecutionResults results =
     *   ksession.execute( CommandFactory.newBatchExecution( cmds ) );
     *
     * // Retrieve the ArrayList
     * results.getValue( "list1" );
     * // Retrieve the inserted Person fact
     * results.getValue( "person" );
     * // Retrieve the query as a QueryResults instance.
     * results.getValue( "Get People" );
     *
     *
     * Example 28. Simple Marshaller Example---------------------------------------------------------------------------------------------
     *
     * // ksession is the KieSession
     * // kbase is the KieBase
     * ByteArrayOutputStream baos = new ByteArrayOutputStream();
     * Marshaller marshaller = KieServices.Factory.get().getMarshallers().newMarshaller( kbase );
     * marshaller.marshall( baos, ksession );
     * baos.close();
     *
     *
     * Example 29. IdentityMarshallingStrategy
     * Example 30. IdentityMarshallingStrategy with Acceptor--------------------------------------------------------------------------------------
     *
     *ByteArrayOutputStream baos = new ByteArrayOutputStream();
     * KieMarshallers kMarshallers = KieServices.Factory.get().getMarshallers()
     * ObjectMarshallingStrategyAcceptor identityAcceptor =
     *         kMarshallers.newClassFilterAcceptor( new String[] { "org.domain.pkg1.*" } );
     * ObjectMarshallingStrategy identityStrategy =
     *         kMarshallers.newIdentityMarshallingStrategy( identityAcceptor );
     * ObjectMarshallingStrategy sms = kMarshallers.newSerializeMarshallingStrategy();
     * Marshaller marshaller =
     *         kMarshallers.newMarshaller( kbase,
     *                                     new ObjectMarshallingStrategy[]{ identityStrategy, sms } );
     * marshaller.marshall( baos, ksession );
     * baos.close();
     *
     *
     * Example 31. Simple example using transactions-------------------------------------------------------------------------------
     * Example 32. Loading a KieSession-------------------------------------------------------------------------------
     * Example 33. Configuring JPA-------------------------------------------------------------------------------
     * Example 34. Configuring JTA DataSource-------------------------------------------------------------------------------
     *
     * Example 38. Utilize and Run - Java -------------------------------------------------------------------------------
     *
     * KieServices ks = KieServices.Factory.get();
     * KieContainer kContainer = ks.getKieClasspathContainer();
     * KieSession kSession = kContainer.newKieSession("ksession2");
     * kSession.setGlobal("out", out);
     *
     * kSession.insert(new Message("Dave", "Hello, HAL. Do you read me, HAL?"));
     * kSession.fireAllRules();
     *
     * kSession.insert(new Message("Dave", "Open the pod bay doors, HAL."));
     * kSession.fireAllRules();
     *
     *
     * Example 48. Utilize and Run - Java-------------------------------------------------------------------------------------
     *
     * @Test
     * public void testSimpleKieBase() {
     *     List<Integer> list = useKieSession("ksession1");
     *     // no packages imported means import everything
     *     assertEquals(4, list.size());
     *     assertTrue( list.containsAll( asList(0, 1, 2, 3) ) );
     * }
     *
     * //.. other tests for ksession2 to ksession6 here
     *
     * private List<Integer> useKieSession(String name) {
     *     KieServices ks = KieServices.Factory.get();
     *     KieContainer kContainer = ks.getKieClasspathContainer();
     *     KieSession kSession = kContainer.newKieSession(name);
     *
     *     List<Integer> list = new ArrayList<Integer>();
     *     kSession.setGlobal("list", list);
     *     kSession.insert(1);
     *     kSession.fireAllRules();
     *
     *     return list;
     * }
     *
     * Example 53. Utilize and Run - Java ------------------------------------------------------------------------------
     *
     * KieServices ks = KieServices.Factory.get();
     * KieRepository kr = ks.getRepository();
     *
     * KieModule kModule = kr.addKieModule(ks.getResources().newFileSystemResource(getFile("default-kiesession")));
     *
     * KieContainer kContainer = ks.newKieContainer(kModule.getReleaseId());
     *
     * KieSession kSession = kContainer.newKieSession();
     * kSession.setGlobal("out", out);
     *
     * Object msg1 = createMessage(kContainer, "Dave", "Hello, HAL. Do you read me, HAL?");
     * kSession.insert(msg1);
     * kSession.fireAllRules();
     *
     *  Example 59. Utilize and Run - Java ------------------------------------------------------------------------------------
     *
     *  KieServices ks = KieServices.Factory.get();
     * KieRepository kr = ks.getRepository();
     * KieFileSystem kfs = ks.newKieFileSystem();
     *
     * kfs.write("src/main/resources/org/kie/example5/HAL5.drl", getRule());
     *
     * KieBuilder kb = ks.newKieBuilder(kfs);
     *
     * kb.buildAll(); // kieModule is automatically deployed to KieRepository if successfully built.
     * if (kb.getResults().hasMessages(Level.ERROR)) {
     *     throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
     * }
     *
     * KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
     *
     * KieSession kSession = kContainer.newKieSession();
     * kSession.setGlobal("out", out);
     *
     * kSession.insert(new Message("Dave", "Hello, HAL. Do you read me, HAL?"));
     * kSession.fireAllRules();
     *
     *
     *
     * Expanded Java source with iterable execution in a stateless KIE session -------------------------------------------------
     * StatelessKieSession ksession = kbase.newStatelessKnowledgeSession();
     * Applicant applicant = new Applicant("Mr John Smith", 16);
     * Application application = new Application();
     *
     * assertTrue(application.isValid());
     * ksession.execute(Arrays.asList(new Object[] { application, applicant }));
     * assertFalse(application.isValid());
     *
     * ksession.execute
     *   (CommandFactory.newInsertIterable(new Object[] { application, applicant }));
     *
     * List<Command> cmds = new ArrayList<Command>();
     * cmds.add(CommandFactory.newInsert(new Person("Mr John Smith"), "mrSmith"));
     * cmds.add(CommandFactory.newInsert(new Person("Mr John Doe"), "mrDoe"));
     *
     * BatchExecutionResults results = ksession.execute(CommandFactory.newBatchExecution(cmds));
     * assertEquals(new Person("Mr John Smith"), results.getValue("mrSmith"));
     *
     * // Set a global `myGlobal` that can be used in the rules.
     * ksession.setGlobal("myGlobal", "I am a global");
     *
     * // Execute while resolving the `myGlobal` identifier.
     * ksession.execute(collection);
     *
     *
     * Out identifiers for globals, inserted facts, and query results ----------------------------------------------------
     *import org.kie.api.runtime.ExecutionResults;
     *
     * // Set up a list of commands.
     * List cmds = new ArrayList();
     * cmds.add(CommandFactory.newSetGlobal("list1", new ArrayList(), true));
     * cmds.add(CommandFactory.newInsert(new Person("jon", 102), "person"));
     * cmds.add(CommandFactory.newQuery("Get People" "getPeople"));
     *
     * // Execute the list.
     * ExecutionResults results = ksession.execute(CommandFactory.newBatchExecution(cmds));
     *
     * // Retrieve the `ArrayList`.
     * results.getValue("list1");
     * // Retrieve the inserted `Person` fact.
     * results.getValue("person");
     * // Retrieve the query as a `QueryResults` instance.
     * results.getValue("Get People");
     *
     *
     *
     */
}
