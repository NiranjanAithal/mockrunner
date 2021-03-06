package com.mockrunner.example.struts;

import org.junit.Before;
import org.junit.Test;

import com.mockrunner.struts.BasicActionTestCaseAdapter;
import com.mockrunner.struts.MapMessageResources;

/**
 * Example test for the {@link ShoppingCartAction}.
 */
public class ShoppingCartActionTest extends BasicActionTestCaseAdapter
{
	@Before
    public void setUp() throws Exception
    {
        super.setUp();
        MapMessageResources resources = new MapMessageResources();
        resources.putMessage("button.add", "Add");
        resources.putMessage("button.order", "Order");
        setResources("test", resources);
    }
    
	@Test
    public void testForward()
    {
        getActionMockObjectFactory().getMockActionMapping().setParameter("method");
        addRequestParameter("method", "Add");
        actionPerform(ShoppingCartAction.class);
        verifyForward("add");
        addRequestParameter("method", "Order");
        actionPerform(ShoppingCartAction.class);
        verifyForward("order");
    }
}
