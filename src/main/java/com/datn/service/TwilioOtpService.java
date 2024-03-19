package com.datn.service;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.TwilioConfig;
import com.twilio.rest.api.v2010.account.*;
import com.twilio.type.*;

@Service
public class TwilioOtpService {
	
	@Autowired
	private TwilioConfig twilioConfig;
	
	
	public void sendOtp(PhoneNumber to, String otp) {
		PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
		String otpMessage = "Your OTP is ##" + otp +"##";
		Message message = Message.creator(to, from, otpMessage).create();
	}
	
	public String generateOtp() {
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
	}
	
	
	
}
