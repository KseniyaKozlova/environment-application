package by.itacademy.environment.controllers;

import by.itacademy.environment.dto.request.CreateCompanyRequestDto;
import by.itacademy.environment.dto.request.UpdateCompanyRequestDto;
import by.itacademy.environment.dto.response.CompanyResponseDto;
import by.itacademy.environment.services.company.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static by.itacademy.environment.util.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = START_PART_URL)
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @ResponseStatus(CREATED)
    @PostMapping(path = SAVE_COMPANY_URL)
    public CompanyResponseDto saveCompany(@RequestBody @Valid CreateCompanyRequestDto companyRequestDto) {
        return companyService.saveCompany(companyRequestDto);
    }

    @GetMapping(path = GET_COMPANY_URL)
    public CompanyResponseDto getCompanyById(@PathVariable(value = ID) UUID id) {
        return companyService.getCompanyResponseById(id);
    }

    @GetMapping(path = GET_COMPANIES_URL)
    public List<CompanyResponseDto> getAllCompanies() {
        return companyService.readCompanies();
    }

    @PutMapping(path = UPDATE_COMPANY_URL)
    public CompanyResponseDto updateCompany(@PathVariable(value = ID) UUID id, @RequestBody UpdateCompanyRequestDto companyRequestDto) {
        return companyService.updateCompany(id, companyRequestDto);
    }

    @DeleteMapping(path = DELETE_COMPANY_URL)
    public void deleteCompany(@PathVariable(value = ID) UUID id) {
        companyService.deleteCompany(id);
    }
}
