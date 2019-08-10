package com.sppcco.customer.util;



import android.os.AsyncTask;

import com.sppcco.core.data.model.Customer;

import java.util.ArrayList;
import java.util.List;


public class AsyncCustomerFilter extends AsyncTask<List<Customer>, Void, List<Customer>> {

  public AsyncResponse delegate = null;

  private List<Customer> mCustomerList;
  private CharSequence mCharSequence;

  public AsyncCustomerFilter(AsyncResponse asyncResponse, List<Customer> customers, CharSequence charSequence) {
    mCustomerList = customers;
    mCharSequence = charSequence;
    delegate = asyncResponse;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
  }

  @Override
  protected List<Customer> doInBackground(List<Customer>[] lists) {

    List<Customer> filteredCustomerList = new ArrayList<>();

    for (Customer customer : mCustomerList) {

      String strCustomerName = customer.getName();
      String strCustomerId = String.valueOf(customer.getId());
      String strCustomerMobileNo = customer.getMobileNo() != null ? customer.getMobileNo() : "0" ;

      if (
        strCustomerName.contains(mCharSequence)
          || strCustomerId.contains(mCharSequence)
          || strCustomerMobileNo.contains(mCharSequence)
        )
        filteredCustomerList.add(customer);
    }

    return filteredCustomerList;
  }

  @Override
  protected void onPostExecute(List<Customer> customers) {
    delegate.processFinish(customers);
  }
}
