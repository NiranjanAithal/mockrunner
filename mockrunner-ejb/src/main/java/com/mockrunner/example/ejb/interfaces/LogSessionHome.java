/*
 * Generated by XDoclet - Do not edit!
 */
package com.mockrunner.example.ejb.interfaces;

/**
 * Home interface for LogSession.
 */
public interface LogSessionHome
   extends javax.ejb.EJBHome
{
   String COMP_NAME="java:comp/env/ejb/LogSession";
   String JNDI_NAME="com/mockrunner/example/LogSession";

   com.mockrunner.example.ejb.interfaces.LogSession create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
