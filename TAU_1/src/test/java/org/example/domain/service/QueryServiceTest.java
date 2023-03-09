package org.example.domain.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class QueryServiceTest {
   private final QueryService queryService = new QueryService();

   @Test
   public void contextTest(){
      assert true;
   }

   @Test
   public void shouldCreateQueryWithCondition(){
      String query = queryService.createQuery("name", "users", "name LIKE 'K%'");
      assert Objects.equals(query, "SELECT name FROM users WHERE name LIKE 'K%'");
   }

   @Test
   public void shouldCreateQueryWithCondition2(){
      String query = queryService.createQuery("name", "users", "name = 'abba'");
      Assert.assertEquals("SELECT name FROM users WHERE name = 'abba'", query);
   }

   @Test
   public void shouldCreateQueryWithTwoColumns(){
      String query = queryService.createQuery("name, surname", "users", "name LIKE 'K%'");
      Assert.assertTrue(Objects.equals(query, "SELECT name, surname FROM users WHERE name LIKE 'K%'"));
   }

   @Test(expected = RuntimeException.class)
   public void shouldNotCreateInvalidQuery(){
      queryService.createQuery("name;", "use;;rs", "name ;LIKE 'K%'");
   }

   @Test
   public void shouldCreateQueryWithEmptyCondition(){
      String query = queryService.createQuery("name", "users", "");
      assert Objects.equals(query, "SELECT name FROM users");
   }

   @Test(expected = RuntimeException.class)
   public void shouldNotCreateQueryWithoutTable(){
      queryService.createQuery("name", "", "name LIKE 'K%'");
   }

   @Test(expected = RuntimeException.class)
   public void shouldNotCreateQueryWithoutColumn(){
      queryService.createQuery("", "users", "name LIKE 'K%'");
   }
}
