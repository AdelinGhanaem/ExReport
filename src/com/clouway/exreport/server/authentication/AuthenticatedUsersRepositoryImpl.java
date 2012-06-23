package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.entites.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AuthenticatedUsersRepositoryImpl implements AuthenticatedUsersRepository {

  private List<Token> tokenList = new ArrayList<Token>();

  public AuthenticatedUsersRepositoryImpl() {
    tokenList.add(new Token("mail"));
  }

  @Override
  public boolean isAuthorized(Token token) {
    return true;
  }

  @Override
  public void persist(Token token) {
    tokenList.add(token);
  }

}
