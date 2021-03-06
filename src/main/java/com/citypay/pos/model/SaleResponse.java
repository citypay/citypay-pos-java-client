/*
 * CityPay POS API
 * CityPay Point of Sale API for payment with card present devices including EMV readers and contactless POS readers.  The API is available from https://github.com/citypay/citypay-pos-api  The API makes it simple to add EMV and contactless card acceptance to iOS, Android, Tablet and desktop applicaitons using a HTTPS protocol. It segregates the complexity of payment processing from the sales environment and eliminates any necessity for the target system to handle card data. 
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
 * SaleResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-09-19T12:47:08.486Z")
public class SaleResponse {
  @SerializedName("success")
  private Boolean success = null;

  @SerializedName("identifier")
  private String identifier = null;

  @SerializedName("description")
  private String description = null;

  public SaleResponse success(Boolean success) {
    this.success = success;
    return this;
  }

   /**
   * Defines whether the sale was successfully created
   * @return success
  **/
  @ApiModelProperty(required = true, value = "Defines whether the sale was successfully created")
  public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public SaleResponse identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

   /**
   * The identifier presented to the sale request
   * @return identifier
  **/
  @ApiModelProperty(example = "Tx12345", required = true, value = "The identifier presented to the sale request")
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public SaleResponse description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Description should the sale request not be accepted
   * @return description
  **/
  @ApiModelProperty(value = "Description should the sale request not be accepted")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SaleResponse saleResponse = (SaleResponse) o;
    return Objects.equals(this.success, saleResponse.success) &&
        Objects.equals(this.identifier, saleResponse.identifier) &&
        Objects.equals(this.description, saleResponse.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, identifier, description);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SaleResponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    identifier: ").append(toIndentedString(identifier)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

