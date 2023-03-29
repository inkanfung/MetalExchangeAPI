package io.carbonchain.hiring.java.app.controller;

import io.carbonchain.hiring.java.ApplicationException;
import io.carbonchain.hiring.java.Controller;
import io.carbonchain.hiring.java.Request;
import io.carbonchain.hiring.java.domain.Asset;
import io.carbonchain.hiring.java.domain.AssetRepository;
import io.carbonchain.hiring.java.domain.Model;
import io.carbonchain.hiring.java.domain.ModelRepository;

import java.util.Arrays;
import java.util.List;

public class ModelsController implements Controller {

  private final AssetRepository assetRepository;
  private final ModelRepository modelRepository;

  public ModelsController(AssetRepository assetRepository, ModelRepository modelRepository) {
    this.assetRepository = assetRepository;
    this.modelRepository = modelRepository;
  }

  public String search(Request request) throws ApplicationException {
    String commodity = request.get(1);
    String assetName = request.get(2);

    Asset asset = assetRepository.findByName(assetName);
    if (asset == null) {
      throw new ApplicationException("Asset not found: " + assetName);
    }

    Model model = modelRepository.findSmallestScopeByCommodityAndAsset(commodity, asset);
    if (model == null) {
      throw new ApplicationException("No model found for commodity: " + commodity);
    }

    String scope = model.getScope() == null ? "Global" : model.getScope();
    return scope + " emission intensity for " + commodity + " is " + model.getEmissionIntensity();
  }


  private Double averageModels(List<Model> models) {
    Double emissionIntensity = models.stream().map(Model::getEmissionIntensity).reduce(0.0, Double::sum);
    return emissionIntensity / models.size();
  }
}
