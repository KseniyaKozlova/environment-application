package by.itacademy.controllers;

import by.itacademy.dto.request.CreateCompanyRequestDto;
import by.itacademy.dto.request.UpdateCompanyRequestDto;
import by.itacademy.dto.response.CompanyResponseDto;
import by.itacademy.services.company.CompanyService;
import by.itacademy.util.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = Constants.START_PART_URL)
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @ResponseStatus(CREATED)
    @PostMapping(path = Constants.SAVE_COMPANY_URL)
    public CompanyResponseDto saveCompany(@RequestBody @Valid CreateCompanyRequestDto companyRequestDto) {
        return companyService.saveCompany(companyRequestDto);
    }

    @GetMapping(path = Constants.GET_COMPANY_URL)
    public CompanyResponseDto getCompanyById(@PathVariable(value = Constants.ID) UUID id) {
        return companyService.getCompanyResponseById(id);
    }

    @GetMapping(path = Constants.GET_COMPANIES_URL)
    public List<CompanyResponseDto> getAllCompanies() {
        return companyService.readCompanies();
    }

    @PutMapping(path = Constants.UPDATE_COMPANY_URL)
    public CompanyResponseDto updateCompany(@PathVariable(value = Constants.ID) UUID id, @RequestBody UpdateCompanyRequestDto companyRequestDto) {
        return companyService.updateCompany(id, companyRequestDto);
    }

    @DeleteMapping(path = Constants.DELETE_COMPANY_URL)
    public void deleteCompany(@PathVariable(value = Constants.ID) UUID id) {
        companyService.deleteCompany(id);
    }
}
