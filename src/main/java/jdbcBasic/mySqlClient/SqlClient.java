package jdbcBasic.mySqlClient;

import java.math.BigDecimal;

public class SqlClient {
    public static void main(String[] args) {

//       OperationInMySql.randomValueForInsertToTowar("towar");
//       OperationInMySql.randomValueForInsertToTowar("towar");
//       OperationInMySql.randomValueForInsertToTowar("towar");
//       OperationInMySql.randomValueForInsertToTowar("towar");
//       OperationInMySql.randomValueForInsertToTowar("towar");
//       OperationInMySql.randomValueForInsertToTowar("towar");

        OperationInMySql.showInformationFromDataBase("towar", "*");
        OperationInMySql.insertToTable(37, "mama", BigDecimal.valueOf(3), 4, 12, "towar");
        OperationInMySql.insertToTablePreparedStatment("towar", 38, "koza", BigDecimal.valueOf(4), 5, 5);
        OperationInMySql.deleteRecordFromTableByIndex(31, "towar");


    }
}
