package com.template.spring_mongodb.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Paging {
    /**
     * UI에서 페이지는 1부터 시작하고 {@link Pageable}에서 페이지는 0부터 시작하기 때문에 이를 보정하기 위해 사용한다.
     *
     * @param pageable 원본 {@link Pageable} 객체
     * @return 값이 조정된 새로운 {@link Pageable} 객체
     */
    public static Pageable of(Pageable pageable) {
        int pageNumber = Math.max(pageable.getPageNumber(), 1) - 1;
        int pageSize = Math.min(pageable.getPageSize(), 300);
        return PageRequest.of(pageNumber, pageSize, pageable.getSort());
    }

    /**
     * 페이징 응답용 Wrapper Class. 페이징 처리된 응답 Dto 클래스에서 @Unwrapped와 같이 사용.
     */
    @AllArgsConstructor
    public static class Response<T> {
        private Page<T> page;

        public List<T> getItems() {
            return page.getContent();
        }

        public long getTotalElements() {
            return page.getTotalElements();
        }

        public int getTotalPages() {
            return page.getTotalPages();
        }
    }
}
