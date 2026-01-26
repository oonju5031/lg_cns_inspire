package com.example.blog;

import com.example.blog.blog.domain.dto.BlogRequestDTO;
import com.example.blog.blog.domain.dto.BlogResponseDTO;
import com.example.blog.blog.repository.BlogMapper;
import com.example.blog.blog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogApplicationTests {

	@Mock  // 가짜 객체 생성
	private BlogMapper blogMapper;

	@InjectMocks  // 가짜 객체 생성 + 의존성 주입(mapper)
	private BlogService blogService;

	@Test
	public void blogWriteGreen() {
		// given
		BlogRequestDTO request = BlogRequestDTO.builder()
											.title("test")
											.content("test")
											.email("admin@admin.com")
											.build();
		when(blogMapper.insertRow(request)).thenReturn(1);  // '테스트 중 blogMapper.insertRow(request)가 호출되면 실제 DB에 접근하지 말고 1을 반환할 것' 을 정의

		// when
		int flag = blogService.write(request);

		// then
		assertEquals(1, flag);
		verify(blogMapper).insertRow(request);
	}

	@Test
	public void blogWriteRed() {
		// given
		BlogRequestDTO request = BlogRequestDTO.builder()
				.content("test")
				.build();

		// when

		// then
		assertThrows(NullPointerException.class,
				() -> blogService.write(request));
		verify(blogMapper, never()).insertRow(request);
	}

	@Test
	public void blogListGreen() {
		// given
		when(blogMapper.selectRow()).thenReturn(List.of(
				BlogResponseDTO.builder().build(),
				BlogResponseDTO.builder().build()
		));

		// when
		List<BlogResponseDTO> list = blogService.list();

		// then
		assertEquals(2, list.size());
		verify(blogMapper).selectRow();
	}

	@Test
	public void blogDeleteGreen() {
		// given
		when(blogMapper.deleteRow(1)).thenReturn(1);

		// when
		int result = blogService.delete(1);

		// then
		assertEquals(1, result);
	}

	@Test
	public void blogUpdateGreen() {
		// given
		BlogRequestDTO request = BlogRequestDTO.builder()
											.title("수정값")
											.content("수정값")
											.build();

		when(blogMapper.updateRow(anyMap())).thenReturn(1);

		// when
		int result = blogService.update(1, request);

		// then
		assertEquals(1, result);
		verify(blogMapper).updateRow(argThat(map ->
				map.get("blogId").equals(1)
						&& map.get("title").equals("수정값")
						&& map.get("content").equals("수정값")
		));
	}

}
