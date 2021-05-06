import {Component, OnInit} from '@angular/core';
import {City} from '../../model/city';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {CityService} from '../../services/city.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  public page: number;
  public pageSize: number;
  public collectionSize: number;

  public city: City = {};
  public cities: City[] = [];


  constructor(private modalService: NgbModal, private cityService: CityService) {
    this.page = 1;
    this.pageSize = 5;
  }

  getCities(): void {
    this.cityService.getAllCity(this.page, this.pageSize)
      .subscribe((cities1: City[]) => {
        this.cities = cities1;
      });
  }

  getSize(): void {
    this.cityService.getSize().subscribe
    ((size: number) => {
      this.collectionSize = size;
    });
  }

  ngOnInit(): void {
    this.getCities();
    this.getSize();
  }

  onPageChanged(pageNum): void {
    this.page = pageNum;
    this.getCities();
  }

  delete(city: City): void {
    this.cityService.delete(city).subscribe(() => {
      this.ngOnInit();
    });
  }

  create(): void {
    this.cityService.create(this.city).subscribe(data => {
        this.city = data;
        alert('Ok');
        this.modalService.dismissAll();
        this.city = {};

      },
      error => {
        alert('Error xxx');
        console.log(error);
      });
    this.modalService.dismissAll();
    this.city = {};
    this.ngOnInit();
  }

  openModal(content): void {
    const modalRef = this.modalService.open(content);
  }

}
