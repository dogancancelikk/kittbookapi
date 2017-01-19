package com.eventz.service;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventz.model.Chapter;
import com.eventz.model.CommentDTO;
import com.eventz.model.Comments;
import com.eventz.model.Post;
import com.eventz.model.Story;
import com.eventz.repository.ChapterRepository;
import com.eventz.repository.CommentDTORepository;
import com.eventz.repository.CommentRepository;
import com.eventz.repository.PostRepository;
import com.eventz.repository.StoryRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepo;
	@Autowired
	CommentDTORepository dtoRepo;
	@Autowired
	StoryRepository storyRepo;
	@Autowired
	PostRepository postRepo;
	@Autowired
	ChapterRepository chapterRepo;
	
	@Override
	public Comments addCommentToStory(Comments comment) {
		if(comment.getTypeID()!=1)
			return null;
		Story s = storyRepo.findOne(comment.getParentID());
		if(s==null)
			return null;
		Integer commentCount = s.getCommentCount();
		if(commentCount==null)
			commentCount=1;
		else
			commentCount++;
		s.setCommentCount(commentCount);
		storyRepo.save(s);
		Calendar now = Calendar.getInstance();
		comment.setCreateTime(now);
		comment.setUpdateTime(now);
		comment.setStatus(1);
		comment.setTypeID(1);
		return commentRepo.save(comment);
	}

	@Override
	public Comments addCommentToChapter(Comments comment) {
		if(comment.getTypeID()!=2)
			return null;
		Chapter c = chapterRepo.findOne(comment.getParentID());
		if(c==null)
			return null;
		Integer commentCount = c.getCommentCount();
		if(commentCount==null)
			commentCount=1;
		else
			commentCount++;
		c.setCommentCount(commentCount);
		chapterRepo.save(c);
		Calendar now = Calendar.getInstance();
		comment.setCreateTime(now);
		comment.setUpdateTime(now);
		comment.setStatus(1);
		comment.setTypeID(2);
		return commentRepo.save(comment);
	}

	@Override
	public Comments updateComment(Comments comment) {
		if(commentRepo.findOne(comment.getId())==null)
			return null;
		Calendar now = Calendar.getInstance();
		comment.setUpdateTime(now);
		return commentRepo.save(comment);
	}

	@Override
	public void deleteComment(Long id) {
		Integer count = 0;
		Comments comment = commentRepo.findOne(id);
		if(comment == null || comment.getStatus()==0)
			return;
		comment.setStatus(0);
		commentRepo.save(comment);
		if(comment.getTypeID()==1)
		{
			Story story = storyRepo.findOne(comment.getParentID());
			count = story.getCommentCount();
			story.setCommentCount(count-1);
			storyRepo.save(story);
		}
		else
		{
			Chapter chapter = chapterRepo.findOne(comment.getParentID());
			count = chapter.getCommentCount();
			chapter.setCommentCount(count-1);
			chapterRepo.save(chapter);
		}
		
	}

	@Override
	public Collection<CommentDTO> getStoryComments(Long id) {
		Collection<CommentDTO> com = dtoRepo.getStoryComments(id);
		if(com==null || com.size()==0)
			return null;
		return com;
	}

	@Override
	public Collection<CommentDTO> getChapterComments(Long id) {
		Collection<CommentDTO> com = dtoRepo.getChapterComments(id);
		if(com==null || com.size()==0)
			return null;
		return com;
	}

	@Override
	public Comments addCommentToPost(Comments comment) {
		if(comment.getTypeID()!=3)
			return null;
		Post p = postRepo.findOne(comment.getParentID());
		if(p==null)
			return null;
		Integer commentCount = p.getCommentCount();
		if(commentCount==null)
			commentCount=1;
		else
			commentCount++;
		p.setCommentCount(commentCount);
		postRepo.save(p);
		Calendar now = Calendar.getInstance();
		comment.setCreateTime(now);
		comment.setUpdateTime(now);
		comment.setStatus(1);
		comment.setTypeID(3);
		return commentRepo.save(comment);
	}

}
