package com.app.ipl.controller;

import com.app.ipl.dto.AppResponse;
import com.app.ipl.dto.TeamDto;
import com.app.ipl.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @Author saurabh vaish
 * @Date 05-07-2022
 */

//@CrossOrigin
@Cacheable("ipl")
@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchController {

    private final MatchService matchService;

    @Operation(summary = "To get a team details with exact name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get team details by name"),
            @ApiResponse(responseCode = "400", description = "Team Not found")
    })
    @GetMapping("/team/{name}")
    public ResponseEntity<AppResponse<TeamDto>> getTeamInfoByName(@PathVariable String name) {
        return new ResponseEntity<>(new AppResponse<>(HttpStatus.OK, "Success", this.matchService.getTeamByName(name)), HttpStatus.OK);
    }

    @Operation(summary = "To get all team details with name like",parameters = {
            @Parameter(required = true,name="name"),
            @Parameter(required = false,name = "pageable")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get all team details by name"),
            @ApiResponse(responseCode = "400", description = "Invalid team name")
    })
    @GetMapping("/team/search/{name}")
    public ResponseEntity<?> getTeamInfoByNameLike(@PathVariable String name,@RequestBody(required = false) Pageable pageable) {
        return new ResponseEntity<>(new AppResponse<>(HttpStatus.OK, "Success", this.matchService.getTeamByNameLike(name, Optional.ofNullable(pageable))), HttpStatus.OK);
    }

    @Operation(summary = "To get a team with match details with exact name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get team details by name"),
            @ApiResponse(responseCode = "400", description = "Team Not found")
    })
    @GetMapping("/team-with-match/{name}")
    public ResponseEntity<AppResponse<TeamDto>> getTeamInfoByNameWithMatchDetails(@PathVariable String name) {
        return new ResponseEntity<>(new AppResponse<>(HttpStatus.OK, "Success", this.matchService.getTeamByNameWithMatchDetails(name)), HttpStatus.OK);
    }

    @Operation(summary = "To get all team with match details with name like")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get all team details by name"),
            @ApiResponse(responseCode = "400", description = "Invalid team name")
    })
    @GetMapping("/team-with-match/search/{name}")
    public ResponseEntity<?> getTeamInfoByNameLikeWithMatchDetails(@PathVariable String name,@RequestBody(required = false) Pageable pageable) {
        return new ResponseEntity<>(new AppResponse<>(HttpStatus.OK, "Success", this.matchService.searchTeamByNameWithMatchDetails(name, Optional.ofNullable(pageable))), HttpStatus.OK);
    }

    @Operation(summary = "To get all matches by team and year ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get all team details by name"),
            @ApiResponse(responseCode = "400", description = "Invalid team name")
    })
    @GetMapping("/team/{name}/match")
    public ResponseEntity<?> getTeamMatchesByNameAndYear(@PathVariable String name, @RequestParam(required = false) int year, @RequestBody(required = false) Pageable pageable) {
        if(year==0)year=LocalDate.now().getYear();
        return new ResponseEntity<>(new AppResponse<>(HttpStatus.OK, "Success", this.matchService.getTeamMatchesByNameAndYear(name,year, Optional.ofNullable(pageable))), HttpStatus.OK);
    }


    @Operation(summary = "To get all team details with name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get all team details by name"),
    })
    @GetMapping("/all-teams")
    public ResponseEntity<?> getAllTeams(@RequestBody(required = false) Pageable pageable) {
        return new ResponseEntity<>(new AppResponse<>(HttpStatus.OK, "Success", this.matchService.getAllTeams(Optional.ofNullable(pageable))), HttpStatus.OK);
    }

}
