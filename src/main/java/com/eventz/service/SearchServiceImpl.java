package com.eventz.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.dto.SearchUserDto;
import com.eventz.model.SearchResultDTO;
import com.eventz.model.User;
import com.eventz.repository.ChapterRepository;
import com.eventz.repository.ChapterTagRepository;
import com.eventz.repository.StoryRepository;
import com.eventz.repository.StoryTagRepository;
import com.eventz.repository.UserRepository;
import com.eventz.response.ResponseSearchUser;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	StoryRepository storyRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	ChapterRepository chapterRepo;
	@Autowired
	StoryTagRepository storyTagRepo;
	@Autowired
	ChapterTagRepository chapterTagRepo;
	
	@Override
	public SearchResultDTO search(String text) {
		String text2 = text;
		String text3 = text;
		
		if(text.contains(" "))
		{
			text2 = text.substring(0, text.indexOf(" "));
			text3 = text.substring(text.indexOf(" ")+1);
		}
			
		text = "%" + text + "%";
		text2 = "%" + text2 + "%";
		text3 = "%" + text3 + "%";
		return new SearchResultDTO
				(
				userRepo.searchUser(text,text2,text3),
				storyRepo.searchStory(text),
				chapterRepo.searchChapter(text),
				storyTagRepo.searchStoryTag(text),
				chapterTagRepo.searchChapterTag(text)
				);
	}
	
	@Override
	public ResponseSearchUser searchUserByUsername(String text) {
		ArrayList<SearchUserDto> reponseList = new ArrayList<SearchUserDto>();
		ArrayList<User> userList = userRepo.searchUserByUsername("%" + text + "%");
		for(User user : userList) {
			reponseList.add(new SearchUserDto(user.getUserName(), user.getName(), user.getSurname(), user.getImage()));
		}
		return new ResponseSearchUser(reponseList);
	}
}
