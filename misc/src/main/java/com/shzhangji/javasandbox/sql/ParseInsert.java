package com.shzhangji.javasandbox.sql;

import lombok.ToString;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.SubSelect;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParseInsert {
  public static void main(String[] args) throws Exception {
    Insert insert = (Insert) CCJSqlParserUtil.parse("INSERT INTO zj_tmp1 (id, name, updated_at) VALUES " +
      "(1, 'Jerry', '2020-09-12 00:00:00')," +
      "(2, 'Mary', '2020-09-12 12:00:00');");

    List<Row> rows = new ArrayList<>();

    insert.getItemsList().accept(new ItemsListVisitor() {
      @Override
      public void visit(ExpressionList expressionList) {
        rows.add(Row.fromExpressionList(expressionList));
      }

      @Override
      public void visit(MultiExpressionList multiExprList) {
        multiExprList.getExprList().forEach(exprList -> {
          rows.add(Row.fromExpressionList(exprList));
        });
      }

      @Override
      public void visit(SubSelect subSelect) {
        throw new UnsupportedOperationException();
      }

      @Override
      public void visit(NamedExpressionList namedExpressionList) {
        throw new UnsupportedOperationException();
      }
    });

    rows.forEach(System.out::println);
  }

  @ToString
  static class Row {
    long id;
    String name;
    Date updatedAt;

    static Row fromExpressionList(ExpressionList expressionList) {
      List<Expression> expressions = expressionList.getExpressions();
      Row row = new Row();
      row.id = Long.parseLong(expressions.get(0).toString());
      row.name = ((StringValue) expressions.get(1)).getValue();

      DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
        row.updatedAt = df.parse(((StringValue) expressions.get(2)).getValue());
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }

      return row;
    }
  }
}
