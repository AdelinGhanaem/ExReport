package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.entites.Account;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountValidator {

  List<String> validateAccount(Account account);

}
