import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Country } from "../country";


@Injectable({
  providedIn: 'root'
})
export class CountryService {

  url = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(this.url + '/countries');
  }

  getCountriesByName(name: string): Observable<Country[]> {
    return this.http.get<Country[]>(this.url + '/country/' + name);
  }
}
