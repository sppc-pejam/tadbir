package com.sppcco.merchandise.ui.select;


import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.merchandise.R;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

public class SelectMerchandiseAdapter extends PagedListAdapter<MerchInfo, SelectMerchandiseViewHolder> {

  private SelectMerchandiseContract.View mView;
  private SelectMerchandiseContract.Presenter mPresenter;

  private SparseArray<MerchMoreInfo> mSparseMerchIdAndMerchMoreInfo;

  SelectMerchandiseAdapter(SelectMerchandiseContract.Presenter searchDialogPresenter,
                           SelectMerchandiseContract.View searchDialogView) {

    super(MerchInfo.DIFF_CALLBACK);

    mView = searchDialogView;
    mPresenter = searchDialogPresenter;

  }


  @NonNull
  @Override
  public SelectMerchandiseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.crd_merchandise, viewGroup, false);

    return new SelectMerchandiseViewHolder(view, this, mPresenter, mView);
  }


  @Override
  public void onBindViewHolder(@NonNull SelectMerchandiseViewHolder holder, int position) {
    MerchInfo merchInfo = getItem(position);

    if (merchInfo != null) {
      holder.bindTo(merchInfo);
    } else {
      holder.clear();
    }
  }

  @Override
  public long getItemId(int position) {
    return super.getItemId(position);
  }

  SparseArray<MerchMoreInfo> getSparseMerchIdAndMerchMoreInfo() {
    if (mSparseMerchIdAndMerchMoreInfo == null)
      mSparseMerchIdAndMerchMoreInfo = new SparseArray<>();
    return mSparseMerchIdAndMerchMoreInfo;
  }

  void setSparseMerchIdAndMerchMoreInfo(SparseArray<MerchMoreInfo> merchMoreInfoSparseArray) {
    mSparseMerchIdAndMerchMoreInfo = merchMoreInfoSparseArray;
  }

  MerchMoreInfo getMerchMoreInfo(int merchId) {
    return getSparseMerchIdAndMerchMoreInfo().get(merchId);
  }

  public static class MerchMoreInfo {

    int merchId;
    int adapterPosition;
    String invTotal;
    String invStock;
    double salesPrice;
    double salesDiscount;
    int colorInvTotal;
    int colorInvStock;

    MerchMoreInfo() {

    }

    static MerchMoreInfo getMerchMoreInfoWithDefaultValue() {

      MerchMoreInfo merchMoreInfo = new MerchMoreInfo();

      merchMoreInfo.setAdapterPosition(-1);
      merchMoreInfo.setInvTotal("");
      merchMoreInfo.setInvStock("");
      merchMoreInfo.setSalesPrice(-1);
      merchMoreInfo.setSalesDiscount(-1);
      merchMoreInfo.setColorInvTotal(-1);
      merchMoreInfo.setColorInvStock(-1);

      return merchMoreInfo;
    }


    public int getMerchId() {
      return merchId;
    }

    public void setMerchId(int merchId) {
      this.merchId = merchId;
    }

    int getAdapterPosition() {
      return adapterPosition;
    }

    void setAdapterPosition(int adapterPosition) {
      this.adapterPosition = adapterPosition;
    }

    String getInvTotal() {
      return invTotal;
    }

    void setInvTotal(String invTotal) {
      this.invTotal = invTotal;
    }

    String getInvStock() {
      return invStock;
    }

    void setInvStock(String invStock) {
      this.invStock = invStock;
    }

    double getSalesPrice() {
      return salesPrice;
    }

    void setSalesPrice(double salesPrice) {
      this.salesPrice = salesPrice;
    }

    double getSalesDiscount() {
      return salesDiscount;
    }

    void setSalesDiscount(double salesDiscount) {
      this.salesDiscount = salesDiscount;
    }

    int getColorInvTotal() {
      return colorInvTotal;
    }

    void setColorInvTotal(int colorInvTotal) {
      this.colorInvTotal = colorInvTotal;
    }

    int getColorInvStock() {
      return colorInvStock;
    }

    void setColorInvStock(int colorInvStock) {
      this.colorInvStock = colorInvStock;
    }
  }

}