package com.swaggertest.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.swaggertest.dto.BookParaDto;
import com.swaggertest.dto.BookViewDto;
import com.swaggertest.repository.BookRepository;
import com.swaggertest.vo.BookVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(tags = "Book")
@RestController
@RequestMapping(value = "/Book")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@ApiOperation(value = "取得書本", notes = "列出所有書本")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/getAllBook", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BookVo> getAll() {
		return bookRepository.findAll();
	}

	@ApiOperation(value = "新增書本", notes = "新增書本的內容")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "存檔成功") })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/createBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookViewDto create(@ApiParam(required = true, value = "書本內容") @RequestBody BookParaDto bookParaDto) {
		BookVo book = new BookVo();
		book.setName(bookParaDto.getName());
		book.setAuthor(bookParaDto.getAuthor());
		book = bookRepository.save(book);
		book.setBookid(book.getBookid());
		
		BookViewDto bookViewDto = new BookViewDto();
		BeanUtils.copyProperties(book, bookViewDto);
		
		return bookViewDto;
	}

	@ApiOperation(value = "取得書本內容", notes = "取得書本內容")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "書本資訊") })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/getBookById/{bookid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookViewDto get(@ApiParam(required = true, name = "bookid", value = "書本ID") @PathVariable Integer bookid) {
		try {
			BookVo book = bookRepository.getById(bookid);
			BookViewDto bookDto = new BookViewDto();
			bookDto.setBookid(book.getBookid());
			bookDto.setName(book.getName());
			bookDto.setAuthor(book.getAuthor());
			return bookDto;
		} catch (EntityNotFoundException entityNotFoundException) {
			return null;
		}
	}
}