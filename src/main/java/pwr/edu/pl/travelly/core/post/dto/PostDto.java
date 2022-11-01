package pwr.edu.pl.travelly.core.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private UUID uuid;
    private String title;
    private String description;
    private LocalDateTime creationTimestamp;
    private LocalDate activeFrom;
    private LocalDate activeTo;
    private String type;
    private int participants;
    private String startPoint;
    private String endPoint;
    private PostAuthorDto postAuthorDto;
    private String profileImageUrl;
    private List<String> imagesUrls;
}
