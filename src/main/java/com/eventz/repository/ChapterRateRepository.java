package com.eventz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventz.model.ChapterRate;
import com.eventz.model.ChapterRateID;

public interface ChapterRateRepository extends JpaRepository<ChapterRate,ChapterRateID> {
	
	public final static String FIND_NEW_RATE =
			"SELECT avg(r.rate) "
			+ "FROM ChapterRate r "
			+ "WHERE r.chapterID = :chapter";
	
	@Query(FIND_NEW_RATE)
	public Double calculateNewRate(@Param("chapter") Long id);
}
