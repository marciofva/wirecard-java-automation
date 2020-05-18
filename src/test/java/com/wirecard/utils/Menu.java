package com.wirecard.utils;

public enum Menu {

	ACCOUNT_SUMMARY("Resumo da conta"),
	ORDERS("Pedidos"),	
	SIGNATURES("Assinaturas"), 	
	BILLING("Cobranças"),	
	FINANCIAL("Financeiro"),
	METRICS("Métricas"),
	CHARGEBACKS("Chargebacks");

	private String value;

	Menu(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
