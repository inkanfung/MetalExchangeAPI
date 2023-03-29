package io.carbonchain.hiring.java.domain;

public class ModelRepository {

  private final Model[] models;

  public ModelRepository(Model[] models) {
    this.models = models;
  }


  public Model findSmallestScopeByCommodityAndAsset(String commodity, Asset asset) {
    Model smallestScopeModel = null;
    for (Model model : models) {
      if (model.isForCommodity(commodity) && (model.getScope() == null || model.getScope().equals(asset.getCountry()) || model.getScope().equals(asset.getContinent()))) {
        if (smallestScopeModel == null || (model.getScope() != null && (smallestScopeModel.getScope() == null || model.getScope().length() < smallestScopeModel.getScope().length()))) {
          smallestScopeModel = model;
        }
      }
    }
    return smallestScopeModel;
  }
}
