import { Component, OnInit } from '@angular/core';
import { CountryService } from '../services/country.service';
import { Country } from '../country';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {

  counties: Country[];
  country: Country;
  displayStyle = 'none';

  constructor(private countryService: CountryService) {
  }

  ngOnInit(): void {
    this.countryService.getCountries().subscribe(response => this.counties = response,
      error => console.log(error));
  }

  fetchCountry(name: string): void {
    this.countryService.getCountriesByName(name).subscribe(response => {
          this.country = response;
      },
      error => console.log(error),
      () => this.displayStyle = 'block');
  }

  closePopup() {
    this.displayStyle = 'none';
  }
}
