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
import com.citypay.pos.model.TransactionData;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * TransactionResult
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-11-22T12:50:27.085Z")
public class TransactionResult {
  @SerializedName("status")
  private String status = null;

  @SerializedName("identifier")
  private String identifier = null;

  @SerializedName("is_complete")
  private Boolean isComplete = null;

  @SerializedName("data")
  private TransactionData data = null;

  public TransactionResult status(String status) {
    this.status = status;
    return this;
  }

   /**
   * A description of the status of a transaction
   * @return status
  **/
  @ApiModelProperty(example = "EMV: Starting transaction", value = "A description of the status of a transaction")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public TransactionResult identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

   /**
   * The indentifier uniquely identifying the transaction
   * @return identifier
  **/
  @ApiModelProperty(example = "Tx12345", value = "The indentifier uniquely identifying the transaction")
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public TransactionResult isComplete(Boolean isComplete) {
    this.isComplete = isComplete;
    return this;
  }

   /**
   * A boolean value stating whether the transaction has completed. The value will be false if the transaction is in progress. Should the value be true, a property containing the transaction details is made available.
   * @return isComplete
  **/
  @ApiModelProperty(value = "A boolean value stating whether the transaction has completed. The value will be false if the transaction is in progress. Should the value be true, a property containing the transaction details is made available.")
  public Boolean isIsComplete() {
    return isComplete;
  }

  public void setIsComplete(Boolean isComplete) {
    this.isComplete = isComplete;
  }

  public TransactionResult data(TransactionData data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")
  public TransactionData getData() {
    return data;
  }

  public void setData(TransactionData data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionResult transactionResult = (TransactionResult) o;
    return Objects.equals(this.status, transactionResult.status) &&
        Objects.equals(this.identifier, transactionResult.identifier) &&
        Objects.equals(this.isComplete, transactionResult.isComplete) &&
        Objects.equals(this.data, transactionResult.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, identifier, isComplete, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionResult {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
    sb.append("    isComplete: ").append(toIndentedString(isComplete)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

