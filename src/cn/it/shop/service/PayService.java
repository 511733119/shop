package cn.it.shop.service;

import java.util.Map;

import cn.it.shop.model.BackData;
import cn.it.shop.model.SendData;

public interface PayService {

	public abstract Map<String, Object> saveDataToRequest(
			Map<String, Object> request, SendData sendData);
	public abstract boolean checkBackData(BackData backData);
}