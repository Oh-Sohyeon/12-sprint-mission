package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.controller.api.BinaryContentApi;
import com.sprint.mission.discodeit.dto.data.BinaryContentDto;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.service.BinaryContentService;
import com.sprint.mission.discodeit.storage.BinaryContentStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/binaryContents")
public class BinaryContentController implements BinaryContentApi {

    private final BinaryContentService binaryContentService;
    private final BinaryContentStorage binaryContentStorage;

    @GetMapping(path = "{binaryContentId}")
    public ResponseEntity<BinaryContentDto> find(@PathVariable("binaryContentId") UUID binaryContentId) {
        BinaryContentDto binaryContent = binaryContentService.find(binaryContentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(binaryContent);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<BinaryContentDto>> findAllByIdIn(@RequestParam("binaryContentIds") List<UUID> binaryContentIds) {
        List<BinaryContentDto> binaryContents = binaryContentService.findAllByIdIn(binaryContentIds);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(binaryContents);
    }

    @GetMapping(path = "{binaryContentId}/download")
    @Override
    public ResponseEntity<?> download(@PathVariable("binaryContentId") UUID binaryContentId) {
        BinaryContentDto binaryContentDto = binaryContentService.find(binaryContentId);
        return binaryContentStorage.download(binaryContentDto);
    }


}
