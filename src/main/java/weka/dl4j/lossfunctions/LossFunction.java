package weka.dl4j.lossfunctions;

import java.io.Serializable;
import java.util.Enumeration;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nd4j.linalg.lossfunctions.ILossFunction;
import weka.core.Option;
import weka.core.OptionHandler;
import weka.dl4j.ApiWrapper;
import weka.dl4j.ApiWrapperUtil;

@EqualsAndHashCode
@ToString
public abstract class LossFunction<T extends ILossFunction>
    implements ApiWrapper<T>, OptionHandler, Serializable {

  private static final long serialVersionUID = 7293731657945995479L;
  T backend;

  @Override
  public void setBackend(T newBackend) {
    backend = newBackend;
  }

  @Override
  public T getBackend() {
    return backend;
  }

  public LossFunction() {
    initializeBackend();
  }

  /**
   * Create an API wrapped schedule from a given ILossFunction object.
   *
   * @param newBackend Backend object
   * @return API wrapped object
   */
  public static LossFunction<? extends ILossFunction> create(ILossFunction newBackend) {
    return (LossFunction<? extends ILossFunction>)
        ApiWrapperUtil.getImplementingWrapper(LossFunction.class, newBackend, "weka.dl4j.lossfunctions");
  }

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration<Option> listOptions() {

    return Option.listOptionsForClass(this.getClass()).elements();
  }

  /**
   * Gets the current settings of the Classifier.
   *
   * @return an array of strings suitable for passing to setOptions
   */
  @Override
  public String[] getOptions() {

    return Option.getOptions(this, this.getClass());
  }

  /**
   * Parses a given list of options.
   *
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  public void setOptions(String[] options) throws Exception {

    Option.setOptions(options, this, this.getClass());
  }
}
