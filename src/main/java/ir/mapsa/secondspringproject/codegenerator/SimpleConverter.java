package ir.mapsa.secondspringproject.codegenerator;

@MyMapper
public interface SimpleConverter {

    SampleEntity convert(SampleDto dto);

    SampleDto convert(SampleEntity dto);
}
