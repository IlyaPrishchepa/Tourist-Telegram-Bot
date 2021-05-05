import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {City} from '../model/city';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  private cityUrl: string;

  constructor(private http: HttpClient) {
    this.cityUrl = 'http://localhost:8080/city';
  }

  public getAllCity(page: number, size: number): Observable<any> {
    page -= 1;
    return this.http.get<City[]>(this.cityUrl + '/find-all?page=' + page + '&size=' + size );
  }

  public getSize(): Observable<any> {
    return this.http.get<number>(this.cityUrl + '/size');
  }

  delete(city: City): Observable<any> {
    return this.http.delete(this.cityUrl + '/' + city.id);
  }

  create(city: City): Observable<City> {
    return this.http.post(this.cityUrl, city);
  }
}
