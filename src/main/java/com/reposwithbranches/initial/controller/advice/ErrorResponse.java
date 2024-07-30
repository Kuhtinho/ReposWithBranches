package com.reposwithbranches.initial.controller.advice;

import lombok.Builder;

@Builder
public record ErrorResponse(Integer status, String message) {
}
