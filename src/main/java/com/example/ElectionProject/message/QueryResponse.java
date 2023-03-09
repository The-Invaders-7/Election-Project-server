package com.example.ElectionProject.message;

import com.example.ElectionProject.models.Voter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponse {
    private List<Voter> voterList;
    private int totalPages;

}
