package com.mockrunner.mock.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mockobjects.sql.MockCallableStatement;
import com.mockobjects.sql.MockDatabaseMetaData;

/**
 * Mock implementation of <code>Connection</code>.
 */
public class MockConnection implements Connection
{
    private List statements = new ArrayList();
    private List preparedStatements = new ArrayList();
    private Map preparedStatementMap = new HashMap();
    private boolean closed = false;
    private boolean autoCommit = false;
    private boolean readOnly = false;
    private int holdability = ResultSet.HOLD_CURSORS_OVER_COMMIT;
    private int level = Connection.TRANSACTION_READ_UNCOMMITTED;
    private Map typeMap = new HashMap();
    private String catalog = null;
    
    public List getStatements()
    {
        return Collections.unmodifiableList(statements);
    }
    
    public void clearStatements()
    {
        statements.clear();
    }
    
    public List getPreparedStatements()
    {
        return Collections.unmodifiableList(preparedStatements);
    }

    public void clearPreparedStatements()
    {
        preparedStatements.clear();
        preparedStatementMap.clear();
    }
      
    public Map getPreparedStatementMap()
    {
        return Collections.unmodifiableMap(preparedStatementMap);
    }
    
    public Statement createStatement() throws SQLException
    {
        MockStatement statement = new MockStatement(this);
        statements.add(statement);
        return statement;
    }
    
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException
    {
        MockStatement statement = new MockStatement(this, resultSetType, resultSetConcurrency);
        statements.add(statement);
        return statement;
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
    {
        MockStatement statement = new MockStatement(this, resultSetType, resultSetConcurrency, resultSetHoldability);
        statements.add(statement);
        return statement;
    }
  
    public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException
    {
        return new MockCallableStatement();
    }

    public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException
    {
        return new MockCallableStatement();
    }

    public CallableStatement prepareCall(String arg0) throws SQLException
    {
        return new MockCallableStatement();
    }
    
    public PreparedStatement prepareStatement(String sql) throws SQLException
    {
        MockPreparedStatement statement = new MockPreparedStatement(this, sql);
        addPreparedStatement(statement);
        return statement;
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
    {
        MockPreparedStatement statement = new MockPreparedStatement(this, sql, resultSetType, resultSetConcurrency);
        addPreparedStatement(statement);
        return statement;
    }
    
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException
    {
        MockPreparedStatement statement = new MockPreparedStatement(this, sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        addPreparedStatement(statement);
        return statement;
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException
    {
        return prepareStatement(sql);
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException
    {
        return prepareStatement(sql);
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException
    {
        return prepareStatement(sql);
    }
    
    private void addPreparedStatement(MockPreparedStatement statement)
    {
        List list = (List)preparedStatementMap.get(statement.getSQL());
        if(null == list)
        {
            list = new ArrayList();
            preparedStatementMap.put(statement.getSQL(), list);
        }
        list.add(statement);
        preparedStatements.add(statement);
    }
    
    public void close() throws SQLException
    {
        closed = true;
    }

    public boolean getAutoCommit() throws SQLException
    {
        return autoCommit;
    }

    public String getCatalog() throws SQLException
    {
        return catalog;
    }

    public int getHoldability() throws SQLException
    {
        return holdability;
    }

    public DatabaseMetaData getMetaData() throws SQLException
    {
        return new MockDatabaseMetaData();
    }

    public int getTransactionIsolation() throws SQLException
    {
        return level;
    }

    public Map getTypeMap() throws SQLException
    {
        return typeMap;
    }

    public SQLWarning getWarnings() throws SQLException
    {
        return null;
    }

    public boolean isClosed() throws SQLException
    {
        return closed;
    }

    public boolean isReadOnly() throws SQLException
    {
        return readOnly;
    }

    public String nativeSQL(String sql) throws SQLException
    {
        return sql;
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException
    {
        this.autoCommit = autoCommit;
    }

    public void setCatalog(String catalog) throws SQLException
    {
        this.catalog = catalog;
    }

    public void setHoldability(int holdability) throws SQLException
    {
        this.holdability = holdability;
    }

    public void setReadOnly(boolean readOnly) throws SQLException
    {
        this.readOnly = readOnly;
    }

    public Savepoint setSavepoint() throws SQLException
    {
        return new MockSavepoint();
    }

    public Savepoint setSavepoint(String name) throws SQLException
    {
        return new MockSavepoint(name);
    }

    public void setTransactionIsolation(int level) throws SQLException
    {
        this.level = level;
    }

    public void setTypeMap(Map typeMap) throws SQLException
    {
        this.typeMap = typeMap;
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException
    {

    }

    public void commit() throws SQLException
    {

    }

    public void rollback() throws SQLException
    {

    }

    public void rollback(Savepoint savepoint) throws SQLException
    {

    }

    public void clearWarnings() throws SQLException
    {

    }
}