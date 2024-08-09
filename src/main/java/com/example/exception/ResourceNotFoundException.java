package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
     String exceptionMessage;
}
