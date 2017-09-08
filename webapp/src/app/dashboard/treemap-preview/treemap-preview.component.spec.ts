import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TreemapPreviewComponent } from './treemap-preview.component';

describe('TreemapPreviewComponent', () => {
  let component: TreemapPreviewComponent;
  let fixture: ComponentFixture<TreemapPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TreemapPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TreemapPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
