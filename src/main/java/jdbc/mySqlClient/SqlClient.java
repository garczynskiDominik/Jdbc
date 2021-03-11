package jdbc.mySqlClient;

import jdbc.mySqlClient.DbConnection;
import jdbc.mySqlClient.OperationInMySql;

import java.math.BigDecimal;

public class SqlClient {
    public static void main(String[] args) {
        OperationInMySql operationInMySql = new OperationInMySql();
        DbConnection dbConnection = new DbConnection();

       operationInMySql.randomValueForInsertToTowar(dbConnection,"towar");
       operationInMySql.randomValueForInsertToTowar(dbConnection,"towar");
       operationInMySql.randomValueForInsertToTowar(dbConnection,"towar");
       operationInMySql.randomValueForInsertToTowar(dbConnection,"towar");
       operationInMySql.randomValueForInsertToTowar(dbConnection,"towar");
       operationInMySql.randomValueForInsertToTowar(dbConnection,"towar");



        OperationInMySql.showInformationFromDataBase(dbConnection, "towar", "*");
        OperationInMySql.insertToTableTowar(dbConnection, 34,"mama", BigDecimal.valueOf(3),4,12,"towar");
      OperationInMySql.deleteRecordFromTable(dbConnection,31,"towar");



    }
}
