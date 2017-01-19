package com.eventz.service;

import java.util.Collection;

import com.eventz.model.CommentDTO;
import com.eventz.model.Comments;

public interface CommentService {

	Comments addCommentToStory(Comments comment);
	
	Comments addCommentToChapter(Comments comment);
	
	Comments addCommentToPost(Comments comment);
	
	Comments updateComment(Comments comment);
	
	void deleteComment(Long id);
	
	Collection<CommentDTO> getStoryComments(Long id);
	
	Collection<CommentDTO> getChapterComments(Long id);
}
