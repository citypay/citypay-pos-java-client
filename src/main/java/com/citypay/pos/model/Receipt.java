/*
 * CityPay POS API
 * CityPay Point of Sale API for payment with card present devices including EMV readers and contactless POS readers.  The API makes it simple to add EMV and contactless card acceptance to iOS, Android, Tablet and desktop applicaitons using a HTTPS protocol. It segregates the complexity of payment processing from the sales environment and eliminates any necessity for the target system to handle card data. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: dev@citypay.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.citypay.pos.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Receipt
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-11-22T10:53:11.095Z")
public class Receipt {
  @SerializedName("customer")
  private String customer = null;

  public Receipt customer(String customer) {
    this.customer = customer;
    return this;
  }

   /**
   * The customer receipt to be printed or stored
   * @return customer
  **/
  @ApiModelProperty(value = "The customer receipt to be printed or stored")
  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Receipt receipt = (Receipt) o;
    return Objects.equals(this.customer, receipt.customer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Receipt {\n");
    
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
