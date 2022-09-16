package com.example.download.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String fileName;
    private String downloadUri;
    private String fileType;
    private Long size;
}
