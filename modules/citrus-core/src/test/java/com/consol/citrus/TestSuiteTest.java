package com.consol.citrus;

import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.consol.citrus.TestCaseMetaInfo.Status;
import com.consol.citrus.actions.EchoBean;
import com.consol.citrus.actions.FailBean;
import com.consol.citrus.report.SimpleLogReporter;

public class TestSuiteTest extends AbstractBaseTest {
    
    @Test
    public void testRunSingleTest() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase = new TestCase();
        testcase.setTestContext(createTestContext());
        testcase.setName("testRunSingleTest");
        TestCaseMetaInfo metaInfo = new TestCaseMetaInfo();
        metaInfo.setStatus(Status.FINAL);
        
        testcase.setMetaInfo(metaInfo);
        
        testcase.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(testcase));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 0);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 1);
    }
    
    @Test
    public void testBeforeSuite() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase = new TestCase();
        testcase.setTestContext(createTestContext());
        testcase.setName("testBeforeSuite");
        TestCaseMetaInfo metaInfo = new TestCaseMetaInfo();
        metaInfo.setStatus(Status.FINAL);
        
        testcase.setMetaInfo(metaInfo);
        
        testcase.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        testsuite.setTasksBefore(Collections.singletonList(new EchoBean()));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(testcase));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 0);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 1);
    }
    
    @Test
    public void testFailBeforeSuite() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase = new TestCase();
        testcase.setTestContext(createTestContext());
        testcase.setName("testFailBeforeSuite");
        TestCaseMetaInfo metaInfo = new TestCaseMetaInfo();
        metaInfo.setStatus(Status.FINAL);
        
        testcase.setMetaInfo(metaInfo);
        
        testcase.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        TestAction failBean = new FailBean();
        testsuite.setTasksBefore(Collections.singletonList(failBean));
        
        Assert.assertFalse(testsuite.beforeSuite());
    }
    
    @Test
    public void testAfterSuite() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase = new TestCase();
        testcase.setTestContext(createTestContext());
        testcase.setName("testBeforeSuite");
        TestCaseMetaInfo metaInfo = new TestCaseMetaInfo();
        metaInfo.setStatus(Status.FINAL);
        
        testcase.setMetaInfo(metaInfo);
        
        testcase.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        testsuite.setTasksAfter(Collections.singletonList(new EchoBean()));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(testcase));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 0);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 1);
    }
    
    @Test
    public void testFailAfterSuite() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase = new TestCase();
        testcase.setTestContext(createTestContext());
        testcase.setName("testFailAfterSuite");
        TestCaseMetaInfo metaInfo = new TestCaseMetaInfo();
        metaInfo.setStatus(Status.FINAL);
        
        testcase.setMetaInfo(metaInfo);
        
        testcase.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        TestAction failBean = new FailBean();
        testsuite.setTasksAfter(Collections.singletonList(failBean));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(testcase));
        Assert.assertFalse(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 0);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 1);
    }
    
    @Test
    public void testTasksBetween() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase1 = new TestCase();
        testcase1.setTestContext(createTestContext());
        testcase1.setName("TestCase1");
        TestCaseMetaInfo metaInfo1 = new TestCaseMetaInfo();
        metaInfo1.setStatus(Status.FINAL);
        
        testcase1.setMetaInfo(metaInfo1);
        
        testcase1.setTestChain(Collections.singletonList(new EchoBean()));
        
        TestCase testcase2 = new TestCase();
        testcase2.setTestContext(createTestContext());
        testcase2.setName("TestCase2");
        TestCaseMetaInfo metaInfo2 = new TestCaseMetaInfo();
        metaInfo2.setStatus(Status.FINAL);
        
        testcase2.setMetaInfo(metaInfo2);
        
        testcase2.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        testsuite.setTasksBetween(Collections.singletonList(new EchoBean()));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 0);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 2);
    }
    
    @Test
    public void testFailTasksBetween() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase1 = new TestCase();
        testcase1.setTestContext(createTestContext());
        testcase1.setName("TestCase1");
        TestCaseMetaInfo metaInfo1 = new TestCaseMetaInfo();
        metaInfo1.setStatus(Status.FINAL);
        
        testcase1.setMetaInfo(metaInfo1);
        
        testcase1.setTestChain(Collections.singletonList(new EchoBean()));
        
        TestCase testcase2 = new TestCase();
        testcase2.setTestContext(createTestContext());
        testcase2.setName("TestCase2");
        TestCaseMetaInfo metaInfo2 = new TestCaseMetaInfo();
        metaInfo2.setStatus(Status.FINAL);
        
        testcase2.setMetaInfo(metaInfo2);
        
        testcase2.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        TestAction failBean = new FailBean();
        testsuite.setTasksBetween(Collections.singletonList(failBean));

        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertFalse(testsuite.run(new TestCase[] {testcase1, testcase2}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 0);
        Assert.assertEquals(testsuite.getCntCasesFail(), 2);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 0);
    }
    
    @Test
    public void testRunTests() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase1 = new TestCase();
        testcase1.setTestContext(createTestContext());
        testcase1.setName("TestCase1");
        TestCaseMetaInfo metaInfo1 = new TestCaseMetaInfo();
        metaInfo1.setStatus(Status.FINAL);
        
        testcase1.setMetaInfo(metaInfo1);
        
        testcase1.setTestChain(Collections.singletonList(new EchoBean()));
        
        TestCase testcase2 = new TestCase();
        testcase2.setTestContext(createTestContext());
        testcase2.setName("TestCase2");
        TestCaseMetaInfo metaInfo2 = new TestCaseMetaInfo();
        metaInfo2.setStatus(Status.FINAL);
        
        testcase2.setMetaInfo(metaInfo2);
        
        testcase2.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));

        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 0);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 2);
    }
    
    @Test
    public void testIncludeTests() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase1 = new TestCase();
        testcase1.setTestContext(createTestContext());
        testcase1.setName("TestCase1");
        TestCaseMetaInfo metaInfo1 = new TestCaseMetaInfo();
        metaInfo1.setStatus(Status.FINAL);
        
        testcase1.setMetaInfo(metaInfo1);
        
        testcase1.setTestChain(Collections.singletonList(new EchoBean()));
        
        TestCase testcase2 = new TestCase();
        testcase2.setTestContext(createTestContext());
        testcase2.setName("TestCase2");
        TestCaseMetaInfo metaInfo2 = new TestCaseMetaInfo();
        metaInfo2.setStatus(Status.FINAL);
        
        testcase2.setMetaInfo(metaInfo2);
        
        testcase2.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));

        testsuite.setIncludeTests(Collections.singletonList("TestCase1"));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 1);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 1);
    }
    
    @Test
    public void testPatternIncludeTests() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase1 = new TestCase();
        testcase1.setTestContext(createTestContext());
        testcase1.setName("TestCase1");
        TestCaseMetaInfo metaInfo1 = new TestCaseMetaInfo();
        metaInfo1.setStatus(Status.FINAL);
        
        testcase1.setMetaInfo(metaInfo1);
        
        testcase1.setTestChain(Collections.singletonList(new EchoBean()));
        
        TestCase testcase2 = new TestCase();
        testcase2.setTestContext(createTestContext());
        testcase2.setName("TestCase2");
        TestCaseMetaInfo metaInfo2 = new TestCaseMetaInfo();
        metaInfo2.setStatus(Status.FINAL);
        
        testcase2.setMetaInfo(metaInfo2);
        
        testcase2.setTestChain(Collections.singletonList(new EchoBean()));
        
        TestCase testcase3 = new TestCase();
        testcase3.setTestContext(createTestContext());
        testcase3.setName("ExcludeTestCase");
        TestCaseMetaInfo metaInfo3 = new TestCaseMetaInfo();
        metaInfo3.setStatus(Status.FINAL);
        
        testcase3.setMetaInfo(metaInfo3);
        
        testcase3.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));

        testsuite.setIncludeTests(Collections.singletonList("TestCase*"));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2, testcase3}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 1);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 2);
        
        testsuite = new TestSuite();
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        testsuite.setIncludeTests(Collections.singletonList("*TestCase"));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2, testcase3}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 2);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 1);
        
        testsuite = new TestSuite();
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        testsuite.setIncludeTests(Collections.singletonList("*TestCase*"));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2, testcase3}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 0);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 3);
    }
    
    @Test
    public void testExcludeTests() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase1 = new TestCase();
        testcase1.setTestContext(createTestContext());
        testcase1.setName("TestCase1");
        TestCaseMetaInfo metaInfo1 = new TestCaseMetaInfo();
        metaInfo1.setStatus(Status.FINAL);
        
        testcase1.setMetaInfo(metaInfo1);
        
        testcase1.setTestChain(Collections.singletonList(new EchoBean()));
        
        TestCase testcase2 = new TestCase();
        testcase2.setTestContext(createTestContext());
        testcase2.setName("TestCase2");
        TestCaseMetaInfo metaInfo2 = new TestCaseMetaInfo();
        metaInfo2.setStatus(Status.FINAL);
        
        testcase2.setMetaInfo(metaInfo2);
        
        testcase2.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));

        testsuite.setExcludeTests(Collections.singletonList("TestCase1"));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 1);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 1);
    }
    
    @Test
    public void testPatternExcludeTests() {
        TestSuite testsuite = new TestSuite();
        
        TestCase testcase1 = new TestCase();
        testcase1.setTestContext(createTestContext());
        testcase1.setName("TestCase1");
        TestCaseMetaInfo metaInfo1 = new TestCaseMetaInfo();
        metaInfo1.setStatus(Status.FINAL);
        
        testcase1.setMetaInfo(metaInfo1);
        
        testcase1.setTestChain(Collections.singletonList(new EchoBean()));
        
        TestCase testcase2 = new TestCase();
        testcase2.setTestContext(createTestContext());
        testcase2.setName("TestCase2");
        TestCaseMetaInfo metaInfo2 = new TestCaseMetaInfo();
        metaInfo2.setStatus(Status.FINAL);
        
        testcase2.setMetaInfo(metaInfo2);
        
        testcase2.setTestChain(Collections.singletonList(new EchoBean()));
        
        TestCase testcase3 = new TestCase();
        testcase3.setTestContext(createTestContext());
        testcase3.setName("ExcludeTestCase");
        
        testcase3.setTestChain(Collections.singletonList(new EchoBean()));
        
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));

        testsuite.setExcludeTests(Collections.singletonList("Exclude*"));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2, testcase3}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 1);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 2);
        
        testsuite = new TestSuite();
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        testsuite.setExcludeTests(Collections.singletonList("*TestCase"));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2, testcase3}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 1);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 2);
        
        testsuite = new TestSuite();
        testsuite.setReporter(Collections.singletonList(new SimpleLogReporter()));
        
        testsuite.setExcludeTests(Collections.singletonList("*TestCase*"));
        
        Assert.assertTrue(testsuite.beforeSuite());
        Assert.assertTrue(testsuite.run(new TestCase[] {testcase1, testcase2, testcase3}));
        Assert.assertTrue(testsuite.afterSuite());
        
        Assert.assertEquals(testsuite.getCntSkipped(), 3);
        Assert.assertEquals(testsuite.getCntCasesFail(), 0);
        Assert.assertEquals(testsuite.getCntCasesSuccess(), 0);
    }
}
