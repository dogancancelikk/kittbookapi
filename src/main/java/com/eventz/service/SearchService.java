package com.eventz.service;

import com.eventz.model.SearchResultDTO;
import com.eventz.response.ResponseSearchUser;

public interface SearchService {

	SearchResultDTO search (String text);

	ResponseSearchUser searchUserByUsername(String text);
}
