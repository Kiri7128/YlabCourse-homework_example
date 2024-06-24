package org.example.manage;

import org.example.workspace.Workspace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

public class ResourceManagerTest {

    private ResourceManager resourceManager;
    @Before
    public void setUp() {
        resourceManager = new ResourceManager();
    }
    @Test
    public void workspaceAdd() {
        Map<String, Workspace> test = resourceManager.getWorkspaces();
        Assert.assertEquals(0,test.size());
        resourceManager.WorkspaceAdd("1","1");
        Assert.assertEquals(1,test.size());
    }




    @Test
    public void workspaceRemove() {
        Map<String, Workspace> test = resourceManager.getWorkspaces();
        resourceManager.WorkspaceAdd("1","1");
        Assert.assertEquals(1,test.size());
        resourceManager.WorkspaceRemove("1");
        Assert.assertEquals(0,test.size());
    }

    @Test
    public void showBookingcurrentDate() {
    }

    @Test
    public void bookingworkspace() {
        resourceManager.WorkspaceAdd("1","1");
        resourceManager.Bookingworkspace("1",new Date(2000),new Date(3000),"1");
        Assert.assertEquals(1,resourceManager.getWorkspacesbooking().size());
        // добавление на дату которая уже забронирована
        resourceManager.Bookingworkspace("1",new Date(2000),new Date(3000),"1");
        Assert.assertEquals(1,resourceManager.getWorkspacesbooking().size());
    }

    @Test
    public void cancelBookingworkspace() {
    }
}