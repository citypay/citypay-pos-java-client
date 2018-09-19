package com.citypay.pos.drivers;

import com.citypay.pos.drivers.kinetic.KineticDeviceDriver;
import com.citypay.pos.drivers.kinetic.KineticJSON;
import com.citypay.pos.drivers.kinetic.KineticTransactionResult;
import com.citypay.pos.model.TransactionResult;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestKineticDeviceDriver {

    @SuppressWarnings("unchecked")
    private static <T> T as(String identifier, String json, Class<T> type) {
        return (T) new KineticJSON(identifier).deserialize(json, type);
    }

    private static TransactionResult adaptTR(String identifier, String json) {
        return KineticDeviceDriver.AdaptTransactionResult(identifier,
                as(identifier, json, KineticTransactionResult.class)
        );
    }


    @Test
    public void testAdaptTransactionResult() {

        TransactionResult result = adaptTR("test1",
                "{\"description\":\"Transaction in progress\",\"status\":\"EMV: Starting transaction\",\"success\":false}");

        assertFalse(result.isSuccess());
        assertFalse(result.isIsComplete());
        assertEquals("test1", result.getIdentifier());
        assertEquals("EMV: Starting transaction", result.getStatus());
        assertNull(result.getData());

        result = adaptTR("test2", "{\"description\":\"No transaction found\",\"success\":false}");
        assertFalse(result.isSuccess());
        assertFalse(result.isIsComplete());
        assertEquals("test2", result.getIdentifier());
        assertEquals("No transaction found", result.getStatus());
        assertNull(result.getData());


    }

    @Test
    public void testAdaptTransactionResultComplete() {

        TransactionResult result = adaptTR("testing8",
                "{\"success\":true,\"testing8\":{\"acquirer_response\":\"approved\",\"additional_data\":\"000800\",\"authorizing_entity\":\"card issuer\",\"auxiliary_data\":\"5536BE16\",\"balance_code\":\"still_out\",\"base_amount\":2,\"batch_total\":\"1135076\",\"card_network\":\"visa\",\"card_presented\":true,\"card_type\":\"EMV\",\"completed_at\":1537359211,\"confirmation_code\":\"0\",\"created_at\":1537359074,\"currency_symbol\":\"£\",\"customer_receipt\":\"%5B%7B%22scroll%22%3A50%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A26%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22CARDHOLDER+COPY%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A22%2C%22text%22%3A%22CityPay+Limited%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22text%22%3A%5B%2219%2F09%2F2018%22%2C%2213%3A13%3A31%22%5D%7D%2C%7B%22text%22%3A%5B%22Terminal+ID%3A%22%2C%22%2A%2A%2A%2A0008%22%5D%7D%2C%7B%22text%22%3A%5B%22Merchant+ID%3A%22%2C%22%2A%2A%2A%2A%2A3382%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A25%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22Visa+Sale%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22text%22%3A%7B%22size%22%3A21%2C%221%22%3A%22Entry+Method%22%2C%222%22%3A%22Contactless%22%7D%7D%2C%7B%22text%22%3A%5B%22Card+%23%22%2C%22%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A0010%22%5D%7D%2C%7B%22text%22%3A%5B%22+%22%2C%22VISA%22%5D%7D%2C%7B%22text%22%3A%5B%22AID%22%2C%22A0000000031010%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22style%22%3A%22bold%22%2C%22text%22%3A%5B%22Total+Amount%22%2C%22%C2%A30.02%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A26%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22AUTH+CODE%3A109438%22%7D%2C%7B%22size%22%3A20%2C%22text%22%3A%22+%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22No+Cardholder+Verification%22%7D%2C%7B%22align%22%3A%22center%22%2C%22text%22%3A%22Please+debit+account+as+shown%22%7D%2C%7B%22align%22%3A%22center%22%2C%22text%22%3A%22Please+retain+for+your+records%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A20%2C%22text%22%3A%22Thank+you+for+your+business%22%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A20%2C%22text%22%3A%22Development+Unit+Only%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A125%7D%5D\",\"cvm_mode\":\"none\",\"data_source\":\"tap\",\"declined_by_card\":false,\"emv_app_cryptogram\":\"7E45D52E6F3B53A0\",\"emv_app_expiration_date\":\"191231\",\"emv_app_txn_counter\":\"0001\",\"emv_authorized_amount\":\"000000000002\",\"emv_cardholder_verification_results\":\"3F0000\",\"emv_country_code\":\"0826\",\"emv_cryptogram_info_data\":\"80\",\"emv_icc_appid\":\"A0000000031010\",\"emv_interchange_profile\":\"2000\",\"emv_issuer_app_data\":\"06011103A00000\",\"emv_pan_seq_num\":\"01\",\"emv_responded\":false,\"emv_terminal_capabilities\":\"E0B8C8\",\"emv_terminal_type\":\"22\",\"emv_terminal_verification_results\":\"0000000000\",\"emv_track2_equivalent\":\"4***********0010\",\"emv_txn_currency_code\":\"0826\",\"emv_txn_date\":\"180919\",\"emv_txn_sequence_counter\":\"6\",\"emv_txn_status_info\":\"0000\",\"emv_txn_type\":\"00\",\"emv_txn_unpredictable_number\":\"F3DEC475\",\"is_api\":true,\"is_closed\":false,\"is_commercial_card\":false,\"is_completed\":true,\"is_contactless\":true,\"is_fallback\":false,\"is_offline\":false,\"is_pending\":false,\"is_sent_to_cloud\":false,\"is_sent_to_host\":false,\"is_voided\":false,\"merchant_receipt\":\"%5B%7B%22scroll%22%3A50%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A26%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22MERCHANT+COPY%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A22%2C%22text%22%3A%22CityPay+Limited%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22text%22%3A%5B%2219%2F09%2F2018%22%2C%2213%3A13%3A31%22%5D%7D%2C%7B%22text%22%3A%5B%22Terminal+ID%3A%22%2C%2233420008%22%5D%7D%2C%7B%22text%22%3A%5B%22Merchant+ID%3A%22%2C%22010003382%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A25%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22Visa+Sale%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22text%22%3A%7B%22size%22%3A21%2C%221%22%3A%22Entry+Method%22%2C%222%22%3A%22Contactless%22%7D%7D%2C%7B%22text%22%3A%5B%22Card+%23%22%2C%22%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A0010%22%5D%7D%2C%7B%22text%22%3A%5B%22Expiry%22%2C%2212%2F19%22%5D%7D%2C%7B%22text%22%3A%5B%22+%22%2C%22VISA%22%5D%7D%2C%7B%22text%22%3A%5B%22AID%22%2C%22A0000000031010%22%5D%7D%2C%7B%22text%22%3A%5B%22Msg+%23%22%2C3%5D%7D%2C%7B%22text%22%3A%5B%22Seq+%23%22%2C%2201%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22style%22%3A%22bold%22%2C%22text%22%3A%5B%22Total+Amount%22%2C%22%C2%A30.02%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A26%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22AUTH+CODE%3A109438%22%7D%2C%7B%22size%22%3A20%2C%22text%22%3A%22+%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22No+Cardholder+Verification%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A125%7D%5D\",\"message_number\":\"3\",\"message_type\":\"debit_presentment\",\"network_type\":\"credit\",\"online_attempted\":true,\"reason\":\"over_floor_limit\",\"response_code\":\"00\",\"response_text\":\"AUTH CODE:109438\",\"result\":\"approved\",\"scheme\":\"visa-qvsdc\",\"session_number\":\"3\",\"signature_required\":false,\"terminal_id\":\"33420008\",\"timing_end\":1537359211,\"timing_logging\":false,\"timing_start\":1537359200,\"total_amount\":2,\"track2_tokenid\":\"4***********0010\",\"transaction_type\":\"sale\",\"uuid\":\"testing8\",\"verified_by_odcvm\":false,\"verified_by_pin\":false,\"was_force_requested\":false}}");

        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertTrue(result.isIsComplete());
        assertEquals("testing8", result.getIdentifier());
        assertEquals("AUTH CODE:109438", result.getStatus());
        assertEquals("000800", result.getData().getAdditionalData());
        assertEquals(new Integer(2), result.getData().getAmount());
        assertEquals("5536BE16", result.getData().getAuxiliaryData());
        assertEquals("EMV", result.getData().getCardType());
        assertEquals("2018-09-19T13:13:31+0100", result.getData().getCompletedAt());
        assertEquals("2018-09-19T13:11:14+0100", result.getData().getCreatedAt());
        assertEquals("£", result.getData().getCurrencySymbol());
        assertEquals("%5B%7B%22scroll%22%3A50%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A26%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22CARDHOLDER+COPY%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A22%2C%22text%22%3A%22CityPay+Limited%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22text%22%3A%5B%2219%2F09%2F2018%22%2C%2213%3A13%3A31%22%5D%7D%2C%7B%22text%22%3A%5B%22Terminal+ID%3A%22%2C%22%2A%2A%2A%2A0008%22%5D%7D%2C%7B%22text%22%3A%5B%22Merchant+ID%3A%22%2C%22%2A%2A%2A%2A%2A3382%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A25%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22Visa+Sale%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22text%22%3A%7B%22size%22%3A21%2C%221%22%3A%22Entry+Method%22%2C%222%22%3A%22Contactless%22%7D%7D%2C%7B%22text%22%3A%5B%22Card+%23%22%2C%22%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A0010%22%5D%7D%2C%7B%22text%22%3A%5B%22+%22%2C%22VISA%22%5D%7D%2C%7B%22text%22%3A%5B%22AID%22%2C%22A0000000031010%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22style%22%3A%22bold%22%2C%22text%22%3A%5B%22Total+Amount%22%2C%22%C2%A30.02%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A26%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22AUTH+CODE%3A109438%22%7D%2C%7B%22size%22%3A20%2C%22text%22%3A%22+%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22No+Cardholder+Verification%22%7D%2C%7B%22align%22%3A%22center%22%2C%22text%22%3A%22Please+debit+account+as+shown%22%7D%2C%7B%22align%22%3A%22center%22%2C%22text%22%3A%22Please+retain+for+your+records%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A20%2C%22text%22%3A%22Thank+you+for+your+business%22%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A20%2C%22text%22%3A%22Development+Unit+Only%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A125%7D%5D", result.getData().getCustomerReceipt());
        assertEquals("none", result.getData().getCvmMode());
//        assertEquals("tap", result.getData().getDatasource());
        assertEquals("7E45D52E6F3B53A0", result.getData().getEmvAppCryptogram());
        assertEquals("191231", result.getData().getEmvAppExpirationDate());
        assertEquals(new Integer(1), result.getData().getEmvAppTxnCounter());
        assertEquals("000000000002", result.getData().getEmvAuthorizedAmount());
        assertEquals("3F0000", result.getData().getEmvCardholderVerificationResults());
        assertEquals("0826", result.getData().getEmvCountryCode());
        assertEquals("80", result.getData().getEmvCryptogramInfoData());
        assertEquals("A0000000031010", result.getData().getEmvIccAppid());
        assertEquals("2000", result.getData().getEmvInterchangeProfile());
        assertEquals("06011103A00000", result.getData().getEmvIssuerAppData());
        assertEquals("01", result.getData().getEmvPanSeqNum());
        assertEquals("E0B8C8", result.getData().getEmvTerminalCapabilities());
        assertEquals("22", result.getData().getEmvTerminalType());
        assertEquals("0000000000", result.getData().getEmvTerminalVerificationResults());
        assertEquals("4***********0010", result.getData().getEmvTrack2Equivalent());
        assertEquals("0826", result.getData().getEmvTxnCurrencyCode());
        assertEquals("180919", result.getData().getEmvTxnDate());
        assertEquals("0000", result.getData().getEmvTxnStatusInfo());
        assertEquals("00", result.getData().getEmvTxnType());
        assertEquals("F3DEC475", result.getData().getEmvTxnUnpredictableNumber());
        assertEquals("testing8", result.getData().getIdentifier());
        assertEquals("%5B%7B%22scroll%22%3A50%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A26%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22MERCHANT+COPY%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A22%2C%22text%22%3A%22CityPay+Limited%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22text%22%3A%5B%2219%2F09%2F2018%22%2C%2213%3A13%3A31%22%5D%7D%2C%7B%22text%22%3A%5B%22Terminal+ID%3A%22%2C%2233420008%22%5D%7D%2C%7B%22text%22%3A%5B%22Merchant+ID%3A%22%2C%22010003382%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A25%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22Visa+Sale%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22text%22%3A%7B%22size%22%3A21%2C%221%22%3A%22Entry+Method%22%2C%222%22%3A%22Contactless%22%7D%7D%2C%7B%22text%22%3A%5B%22Card+%23%22%2C%22%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A%2A0010%22%5D%7D%2C%7B%22text%22%3A%5B%22Expiry%22%2C%2212%2F19%22%5D%7D%2C%7B%22text%22%3A%5B%22+%22%2C%22VISA%22%5D%7D%2C%7B%22text%22%3A%5B%22AID%22%2C%22A0000000031010%22%5D%7D%2C%7B%22text%22%3A%5B%22Msg+%23%22%2C3%5D%7D%2C%7B%22text%22%3A%5B%22Seq+%23%22%2C%2201%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22style%22%3A%22bold%22%2C%22text%22%3A%5B%22Total+Amount%22%2C%22%C2%A30.02%22%5D%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22size%22%3A26%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22AUTH+CODE%3A109438%22%7D%2C%7B%22size%22%3A20%2C%22text%22%3A%22+%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22align%22%3A%22center%22%2C%22style%22%3A%22bold%22%2C%22text%22%3A%22No+Cardholder+Verification%22%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A20%7D%2C%7B%22scroll%22%3A125%7D%5D", result.getData().getMerchantReceipt());
        assertEquals("00", result.getData().getResponseCode());
        assertEquals("AUTH CODE:109438", result.getData().getResponseText());
        assertEquals("approved", result.getData().getResult());
        assertEquals("visa-qvsdc", result.getData().getScheme());
        assertEquals("33420008", result.getData().getTerminalId());
        assertEquals(new Integer(2), result.getData().getTotalAmount());
        assertEquals("sale", result.getData().getTransactionType());
        assertEquals(new Boolean(true), result.getData().isCardPresented());
        assertEquals(new Boolean(false), result.getData().isDeclinedByCard());
        assertEquals(new Boolean(false), result.getData().isIsClosed());
        assertEquals(new Boolean(false), result.getData().isIsCommercialCard());
        assertEquals(new Boolean(true), result.getData().isIsCompleted());
        assertEquals(new Boolean(true), result.getData().isIsContactless());
        assertEquals(new Boolean(false), result.getData().isIsFallback());
        assertEquals(new Boolean(false), result.getData().isIsOffline());
        assertEquals(new Boolean(false), result.getData().isIsVoided());
        assertEquals(new Boolean(false), result.getData().isSignatureRequired());
        assertEquals(new Boolean(false), result.getData().isVerifiedByPin());



    }

    @Test
    public void testAdaptTransactionResultVoided() {

        TransactionResult result = adaptTR("testing7", "{\"success\":true,\"testing7\":{\"base_amount\":1,\"card_presented\":false,\"created_at\":1537352942,\"declined_by_card\":false,\"emv_responded\":false,\"is_api\":true,\"is_closed\":false,\"is_commercial_card\":false,\"is_completed\":true,\"is_contactless\":false,\"is_fallback\":false,\"is_offline\":false,\"is_pending\":false,\"is_sent_to_cloud\":true,\"is_sent_to_host\":false,\"is_voided\":false,\"network_type\":\"credit\",\"online_attempted\":false,\"reason\":\"over_floor_limit\",\"response_text\":\"Transaction Void\",\"result\":\"cancelled\",\"signature_required\":false,\"timing_end\":1537357264,\"timing_logging\":false,\"total_amount\":1,\"transaction_type\":\"sale\",\"uuid\":\"testing7\",\"verified_by_odcvm\":false,\"verified_by_pin\":false,\"was_force_requested\":false}}");
        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertTrue(result.isIsComplete());
        assertEquals("testing7", result.getIdentifier());
        assertEquals("Transaction Void", result.getStatus());

    }


}
